package com.bj.house.common.utils;

import com.bj.house.common.entity.User;
import com.bj.house.common.model.UserModel;

/**
 *
 * ModelAdapter :将Vo的Model转换为entity Model
 * Created by BJ on 2018/1/20.
 */
public class ModelAdapter {

    //转换为User model
    public static User ToUser(UserModel model){
        User user = new User();
        //TODO 设置判断：如果不为空才可存入user，防止null将初始值给覆盖掉
        if (model.getId() != null)
            user.setId(model.getId());
        if (model.getEmail() != null)
            user.setEmail(model.getEmail());
        if (model.getPhone() != null)
            user.setPhone(model.getPhone());
        if (model.getName() != null)
            user.setName(model.getName());
        if (model.getPasswd() != null)
            user.setPasswd(model.getPasswd());
        if (model.getType() != null)
            user.setType(model.getType());
        if (model.getEnable() != null)
            user.setEnable(model.getEnable());
        if (model.getAvatar() != null)
            user.setAvatar(model.getAvatar());
        if (model.getAgencyId() != null)
            user.setAgencyId(model.getAgencyId());
        if (model.getAboutme() != null)
            user.setAboutme(model.getAboutme());
        return user;
    }

    //转换为User model
    public static UserModel ToUserModel(User user){
        UserModel model = new UserModel();
        if (user.getId() != null)
            model.setId(user.getId());
        if (user.getEmail() != null)
            model.setEmail(user.getEmail());
        if (user.getPhone() != null)
            model.setPhone(user.getPhone());
        if (user.getName() != null)
            model.setName(user.getName());
        if (user.getPasswd() != null)
            model.setPasswd(user.getPasswd());
        if (user.getType() != null)
            model.setType(user.getType());
        if (user.getEnable() != null)
            model.setEnable(user.getEnable());
        if (user.getAvatar() != null)
            model.setAvatar(user.getAvatar());
        if (user.getAgencyId() != null)
            model.setAgencyId(user.getAgencyId());
        if (user.getAboutme() != null)
            model.setAboutme(user.getAboutme());
        return model;
    }

    //更新User操作，为空的信息不替换
    public static User updateToUser(User oldUser,UserModel model){
        //如果不为空才可存入user，防止null将原来值给覆盖掉
        if (model.getPhone() != null)
            oldUser.setPhone(model.getPhone());
        if (model.getName() != null)
            oldUser.setName(model.getName());
        if (model.getPasswd() != null)
            oldUser.setPasswd(model.getPasswd());
        if (model.getType() != null)
            oldUser.setType(model.getType());
        if (model.getEnable() != null)
            oldUser.setEnable(model.getEnable());
        if (model.getAvatar() != null)
            oldUser.setAvatar(model.getAvatar());
        if (model.getAgencyId() != null)
            oldUser.setAgencyId(model.getAgencyId());
        if (model.getAboutme() != null)
            oldUser.setAboutme(model.getAboutme());
        return oldUser;
    }




}
