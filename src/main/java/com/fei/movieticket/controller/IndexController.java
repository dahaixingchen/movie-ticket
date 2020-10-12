package com.fei.movieticket.controller;

import com.fei.movieticket.elasticSearch.UserDAO;
import com.fei.movieticket.elasticSearch.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

/**
 * Create by wangxb
 * 2019-09-08 18:54
 */
@RestController
public class IndexController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping("/addUser")
    public UserEntity addUser(@RequestBody UserEntity user) {
        return userDAO.save(user);
    }

    @RequestMapping("/findUser")
    public Optional<UserEntity> findUser(String id) {
        Optional<UserEntity> byId = userDAO.findById(id);
        return byId;
    }

}