package com.bj.house.web.controller;

import com.bj.house.common.entity.User;
import com.bj.house.biz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by BJ on 2018/1/12.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    public List<User> getUsers(){
        return userService.getUsers();
    }

}
