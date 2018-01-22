package com.bj.house.web;

import com.bj.house.biz.service.UserService;
import com.bj.house.common.model.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by BJ on 2018/1/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//可以指定端口进行测试
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AuthTests {

    @Autowired
    private UserService userService;

    @Test
    public void testAuth() {
        UserModel user = userService.auth("rosejeck@126.com", "111111");
        assert user != null;
        System.out.println(user.getAboutme());
    }

}
