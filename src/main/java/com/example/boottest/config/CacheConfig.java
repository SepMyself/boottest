package com.example.boottest.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class CacheConfig {
    @Value("${springext.cache.redis.topic:cache}")
    String topicName;

    @Bean
    public TwoLevelCacheManager cacheManager(StringRedisTemplate redisTemplate){
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(redisTemplate.getConnectionFactory());
        RedisSerializationContext.SerializationPair pair = RedisSerializationContext.SerializationPair
                .fromSerializer(new JdkSerializationRedisSerializer(this.getClass().getClassLoader()));
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
        TwoLevelCacheManager cacheManager = new TwoLevelCacheManager(redisTemplate, writer, config);

        return cacheManager;
    }

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic(topicName));
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(final TwoLevelCacheManager cacheManager){
        return new MessageListenerAdapter(new MessageListener() {
            public void onMessage(Message message, byte[] pattern) {
                byte[] bs = message.getChannel();
                try {
                    String type = new String(bs, "UTF-8");
                    String cacheName = new String(message.getBody(), "UTF-8");
                    cacheManager.receiver(cacheName);
                } catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }
            }
        });
    }

    class TwoLevelCacheManager extends RedisCacheManager {
        RedisTemplate redisTemplate;
        public TwoLevelCacheManager(RedisTemplate redisTemplate, RedisCacheWriter cacheWriter,
                                    RedisCacheConfiguration defaultCacheConfiguration) {
            super(cacheWriter, defaultCacheConfiguration);
            this.redisTemplate = redisTemplate;
        }

        @Override
        protected Cache decorateCache(Cache cache){
            return new RedisAndLocalCache(this, (RedisCache)cache);
        }

        public void publishMessage(String cacheName){
            this.redisTemplate.convertAndSend(topicName, cacheName);
        }

        public void receiver(String name){
            RedisAndLocalCache cache = ((RedisAndLocalCache) this.getCache(name));
            if(cache != null){
                cache.clearLocal();
            }
        }
    }

    class RedisAndLocalCache implements Cache {
        ConcurrentHashMap<Object, Object> local = new ConcurrentHashMap<Object, Object>();

        RedisCache redisCache;
        TwoLevelCacheManager cacheManager;

        public RedisAndLocalCache(TwoLevelCacheManager cacheManager, RedisCache redisCache){
            this.redisCache = redisCache;
            this.cacheManager = cacheManager;
        }

        @Override
        public String getName(){
            return redisCache.getName();
        }


        @Override
        public Object getNativeCache(){
            return redisCache.getNativeCache();
        }

        @Override
        public ValueWrapper get(Object key){
            ValueWrapper wrapper = (ValueWrapper) local.get(key);
            if(wrapper != null){
                return wrapper;
            } else {
                wrapper = redisCache.get(key);
                if(wrapper != null){
                    local.put(key, wrapper);
                }

                return wrapper;
            }
        }

        @Override
        public <T> T get(Object key, Class<T> type){
            return redisCache.get(key, type);
        }

        @Override
        public <T> T get(Object key, Callable<T> valueLoader){
            return redisCache.get(key, valueLoader);
        }

        @Override
        public void put(Object key, Object value){
            System.out.println(value.getClass().getClassLoader());
            redisCache.put(key, value);
            clearOtherJVM();
        }

        protected void clearOtherJVM(){
            cacheManager.publishMessage(redisCache.getName());
        }

        @Override
        public ValueWrapper putIfAbsent(Object key, Object value){
            ValueWrapper v = redisCache.putIfAbsent(key, value);
            clearOtherJVM();
            return v;
        }

        @Override
        public void evict(Object key){
            redisCache.evict(key);
            clearOtherJVM();
        }

        @Override
        public void clear(){
            redisCache.clear();
        }

        public void clearLocal(){
            this.local.clear();
        }
    }
}
