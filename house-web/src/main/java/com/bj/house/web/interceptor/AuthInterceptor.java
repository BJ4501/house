package com.bj.house.web.interceptor;

import com.bj.house.common.constants.CommonConstants;
import com.bj.house.common.model.UserModel;
import com.google.common.base.Joiner;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Controller拦截器，用户身份鉴权
 * Created by BJ on 2018/1/20.
 */
@Component
public class AuthInterceptor implements HandlerInterceptor{

    //在Controller方法执行之前拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取ParameterMap
        Map<String,String[]> map = request.getParameterMap();
        map.forEach((k,v) ->{
            //将这三种消息，设置为Attribute
            if (k.equals("errorMsg")||k.equals("successMsg")||k.equals("target")){
                request.setAttribute(k, Joiner.on(",").join(v));
            }
        });

        String reqUri = request.getRequestURI();
        //错误页面和静态资源不需要拦截
        if (reqUri.startsWith("/static")||reqUri.startsWith("/error")){
            return true;
        }
        //当Session不存在的时候自动创建
        HttpSession session = request.getSession(true);
        UserModel userModel = (UserModel) session.getAttribute(CommonConstants.USER_ATTRIBUTE);
        if (userModel != null){
            //NOTE：之后就可以在业务代码中，获取userModel对象了
            UserContext.setUserModel(userModel);
        }
        return true;
    }

    //在Controller方法执行之后拦截
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //页面渲染之后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.remove();
    }
}
