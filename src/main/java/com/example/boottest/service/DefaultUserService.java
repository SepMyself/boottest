package com.example.boottest.service;

//import com.example.boottest.dao.UserDao;
import com.example.boottest.dao.entity.User;
import com.example.boottest.dao.repository.UserRepository;
import com.example.boottest.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultUserService implements UserService {
    @Autowired
    UserRepository dao;

    public Integer addUser(User user){
        if(user.getId() == null){
            user.setCreateTime(TimeUtil.now());
        }
        dao.save(user);
//        Integer id = user.getId();
//        user.setName(id + user.getName());
//        dao.save(user);
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
}
