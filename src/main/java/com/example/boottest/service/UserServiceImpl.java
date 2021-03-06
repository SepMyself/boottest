package com.example.boottest.service;

import com.example.boottest.dao.entity.User;
import com.example.boottest.dao.repository.RoleRepository;
import com.example.boottest.dao.repository.UserRepository;
import com.example.boottest.dto.UserMapper;
import com.example.boottest.dto.UserRegisterDto;
import com.example.boottest.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
//@CacheConfig(cacheNames = {"user"} )
public class UserServiceImpl implements UserService, UserDetailsService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public Integer addUser(User user){
        if(user.getId() == null){
            Date now = TimeUtil.nowDate();
            user.setCreateTime(now);
            user.setLastLoginTime(now);
            user.setLastPasswordResetDate(now);
        }
        userRepository.save(user);
        return user.getId();
    }

    @Cacheable(value = "userById")
    public UserRegisterDto findById(Integer id){
        User xx = userRepository.getOne(id);
        return UserMapper.INSTANCE.entityToDto(xx);
    }

    public List<User> findAll(int page, int size){
        PageRequest pagealbe = PageRequest.of(page, size);
        Page<User> pageObject = userRepository.findAll(pagealbe);
        int totalPage = pageObject.getTotalPages();
        long count = pageObject.getTotalElements();
        return pageObject.getContent();
    }

    public User findByUsername(String username){
        User user = userRepository.findByUsername(username);
        return user;
    }

    // https://blog.csdn.net/linzhiqiang0316/article/details/78358907
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return findByUsername(username);
    }
}
