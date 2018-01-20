package com.bj.house.common.utils;

import com.bj.house.common.entity.User;
import com.bj.house.common.model.UserModel;

/**
 *
 * ModelAdapter :将Vo的Model转换为entity Model
 * Created by BJ on 2018/1/20.
 */
public class ModelAdapter {

/*
    private Long id;
    private String email;
    private String phone;
    private String name;
    private String passwd;
    private String confirmPasswd;
    //1-普通用户 2-经纪人
    private Integer type;
    private Date createTime;
    private Integer enable;
    private String avator;
    private MultipartFile avatorFile;
    private String newPassword;
    private String key;
    private Long agencyId;
*/


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
        if (model.getAvator() != null)
            user.setAvatar(model.getAvator());
        if (model.getAgencyId() != null)
            user.setAgencyId(model.getAgencyId());
        if (model.getAboutme() != null)
            user.setAboutme(model.getAboutme());
        return user;
    }


}
