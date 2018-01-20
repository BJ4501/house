package com.bj.house.web.interceptor;

import com.bj.house.common.model.UserModel;

/**
 * Created by BJ on 2018/1/20.
 */
public class UserContext {

    private static final ThreadLocal<UserModel> USER_HOLDER = new ThreadLocal<>();

    public static void setUserModel(UserModel model){
        USER_HOLDER.set(model);
    }

    public static void remove(){
        USER_HOLDER.remove();
    }

    public static UserModel getUserModel(){
        return USER_HOLDER.get();
    }

}
