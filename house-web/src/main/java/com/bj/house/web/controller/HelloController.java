package com.bj.house.web.controller;

import com.bj.house.common.entity.User;
import com.bj.house.biz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
        if (one != null)
            throw new IllegalArgumentException();

        modelMap.put("user",one);
        return "hello";
    }

    //定义一个Controller转到首页
    @RequestMapping("index")
    public String index(){
        return "homepage/index";
    }


}
