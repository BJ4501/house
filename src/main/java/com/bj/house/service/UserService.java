package com.bj.house.service;

import com.bj.house.common.model.User;
import com.bj.house.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BJ on 2018/1/12.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getUsers(){
        return userMapper.selectUsers();
    }


}
