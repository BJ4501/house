package com.bj.house.web.controller;

import com.bj.house.common.model.UserModel;
import com.bj.house.common.result.ResultMsg;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by BJ on 2018/1/20.
 */
public class UserHelper {

    //验证功能
    public static ResultMsg validate(UserModel account){
        if (StringUtils.isBlank(account.getEmail())){
            return ResultMsg.errorMsg("Email 有误");
        }
        if (StringUtils.isBlank(account.getName())){
            return ResultMsg.errorMsg("Name 有误");
        }
        if (StringUtils.isBlank(account.getConfirmPasswd())
                ||StringUtils.isBlank(account.getPasswd())
                ||!account.getPasswd().equals(account.getConfirmPasswd())){
            return ResultMsg.errorMsg("密码为空或两次输入的密码不同");
        }
        if (account.getPasswd().length() < 6){
            return ResultMsg.errorMsg("密码需要大于6位");
        }
        return ResultMsg.successMsg("Success");
    }
}
