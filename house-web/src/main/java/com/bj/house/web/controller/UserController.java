package com.bj.house.web.controller;

import com.bj.house.biz.service.UserService;
import com.bj.house.common.constants.CommonConstants;
import com.bj.house.common.entity.User;
import com.bj.house.common.model.UserModel;
import com.bj.house.common.result.ResultMsg;
import com.bj.house.common.utils.HashUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by BJ on 2018/1/12.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //注册提交：流程
    //接收注册表单->对表单进行验证->验证通过添加用户并且发送激活邮件
    //如果表单验证失败，返回错误信息
    /**
     * 注册页面获取：根据account对象有无来判断
     * @param account
     * @param modelmap
     * @return
     */
    @RequestMapping("accounts/register")
    public String accountsRegister(UserModel account, ModelMap modelmap){
        if (account == null|| account.getName() == null){
            return "/user/accounts/register";
        }
        //用户验证
        ResultMsg resultMsg = UserHelper.validate(account);
        if (resultMsg.isSuccess()&& userService.addAccount(account)){
            modelmap.put("email",account.getEmail());
            return "/user/accounts/registerSubmit";
        }else {
            return "redirect:/accounts/register?"+resultMsg.asUrlParams();
        }
    }

    //用户点击激活邮件的接口
    @RequestMapping("accounts/verify")
    public String verify(String key){
        boolean result = userService.enable(key);
        if (result) {
            return "redirect:/index?" + ResultMsg.successMsg("激活成功").asUrlParams();
        } else {
            return "redirect:/accounts/register?" + ResultMsg.errorMsg("激活失败,请确认链接是否过期");
        }
    }

    //---登录流程---

    @RequestMapping("accounts/signin")
    public String signIn(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String target = request.getParameter("target");
        //如果都为空，认为是一个登录页面的请求
        if (username == null || password == null){
            request.setAttribute("target",target);
            return "/user/accounts/signin";
        }
        //用户名密码校验
        UserModel userModel = userService.auth(username,password);
        if (userModel == null){
            return "redirect:/accounts/signin?" + "target=" + target + "&username=" + username + "&"
                    + ResultMsg.errorMsg("用户名或密码错误").asUrlParams();
        }else {
            //true：如果没有就强制创建session
            HttpSession session = request.getSession(true);
            session.setAttribute(CommonConstants.USER_ATTRIBUTE,userModel);
            session.setAttribute(CommonConstants.PLAIN_USER_ATTRIBUTE,userModel);
            return StringUtils.isNoneBlank(target) ? "redirect:" + target : "redirect:/index";
        }
    }

    /**
     * 登出操作
     * @param request
     * @return
     */
    @RequestMapping("accounts/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        //注销掉原所有session信息
        session.invalidate();
        return "redirect:/index";
    }

    //---个人信息页---

    /**
     * 1.提供个人信息页面
     * 2.更新用户信息
     * @param updateUser
     * @param model
     * @return
     */
    @RequestMapping("accounts/profile")
    public String profile(HttpServletRequest request, UserModel updateUser, ModelMap model){
        if (updateUser.getEmail() == null){
            //因为Email信息是不可更改的
            //前端如果不返回Email信息，则说明是查看个人信息，反之，更改用户信息
            return "/user/accounts/profile";
        }
        userService.updateUser(updateUser,updateUser.getEmail());

        UserModel query = new UserModel();
        query.setEmail(updateUser.getEmail());

        //重新设置session
        UserModel userModel = userService.getUserByQuery(query);
        request.getSession(true).setAttribute(CommonConstants.USER_ATTRIBUTE,userModel);
        return "redirect:/accounts/profile?" + ResultMsg.successMsg("更新成功").asUrlParams();
    }

    /**
     * 修改密码操作
     * @return
     */
    @RequestMapping("accounts/changePassword")
    public String changePassword(String email, String password, String newPassword,
                                 String confirmPassword, ModelMap mode) {
        UserModel model = userService.auth(email,password);
        if (model == null || !confirmPassword.equals(newPassword)){
            return "redirect:/accounts/profile?" + ResultMsg.errorMsg("密码错误").asUrlParams();
        }

        UserModel updateUser = new UserModel();
        updateUser.setPasswd(HashUtils.encryPassword(newPassword));
        userService.updateUser(updateUser,email);
        return "redirect:/accounts/profile?" + ResultMsg.successMsg("更新成功").asUrlParams();
    }


}
