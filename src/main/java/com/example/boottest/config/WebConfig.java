package com.example.boottest.config;

import com.example.boottest.config.interceptors.AuthenticationInterceptor;
import com.example.boottest.config.interceptors.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.util.UrlPathHelper;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/**");
        //registry.addInterceptor(new AuthorizationInterceptor()).addPathPatterns("/hello");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer){
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(false).maxAge(3600);
    }

    @Override
    public void addFormatters(FormatterRegistry registry){
        registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/index.html").setViewName("/index.ftl");
        registry.addRedirectViewController("/**/*.do", "/index.html");
    }
}
