package com.bj.house.controller;

import com.bj.house.persistance.entity.User;
import com.bj.house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by BJ on 2018/1/19.
 */
@Controller
public class HelloController {

    @Autowired
    UserService userService;

    @RequestMapping("hello")
    public String hello(ModelMap modelMap){
        List<User> users = userService.getUsers();
        User one = users.get(0);
        modelMap.put("user",one);
        return "hello";
    }


}
