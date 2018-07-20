package com.example.boottest.service;

//import com.example.boottest.dao.UserDao;
import com.example.boottest.dao.entity.Role;
import com.example.boottest.dao.entity.User;
import com.example.boottest.dao.repository.RoleRepository;
import com.example.boottest.dao.repository.UserRepository;
import com.example.boottest.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultUserService implements UserService {
    @Autowired
    UserRepository dao;
    @Autowired
    RoleRepository roleRepository;
//    @Autowired
//    UserRoleRepository userRoleRepository;

    public Integer addUser(User user){
        if(user.getId() == null){
            Date now = TimeUtil.nowDate();
            user.setCreateTime(now);
            user.setLastPasswordResetTime(now);
        }
        dao.save(user);
        return user.getId();
    }

    public User findById(Integer id){
        //Optional<User> result = dao.findById(id);
        //return result.get();

        User xx = dao.getOne(id);
        return xx;
    }

    public List<User> findAll(int page, int size){
        PageRequest pagealbe = PageRequest.of(page, size);
        Page<User> pageObject = dao.findAll(pagealbe);
        int totalPage = pageObject.getTotalPages();
        long count = pageObject.getTotalElements();
        return pageObject.getContent();
    }

    @Override
    public User findByUsername(String username){
        return dao.findByUsername(username);
    }
}
