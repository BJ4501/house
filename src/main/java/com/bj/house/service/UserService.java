package com.bj.house.service;

import com.bj.house.persistance.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BJ on 2018/1/12.
 */
@Service
public class UserService {

   /* @Autowired
    private UserMapper userMapper;*/

    public List<User> getUsers(){

        List<User> tmp = new ArrayList<>();
        User user = new User();
        user.setName("dsadsad");
        tmp.add(user);

        return tmp;
    }


}
