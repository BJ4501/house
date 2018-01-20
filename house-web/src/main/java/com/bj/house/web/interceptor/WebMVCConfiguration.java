package com.bj.house.web.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 决定拦截器启动顺序
 * Created by BJ on 2018/1/20.
 */
@Configuration
public class WebMVCConfiguration extends WebMvcConfigurerAdapter{

    @Autowired
    private AuthInterceptor authInterceptor;

    @Autowired
    private AuthActionInterceptor authActionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .excludePathPatterns("/static") //排除不去拦截的Url
                .addPathPatterns("/**"); //** 需要拦截除了static的所有

        registry.addInterceptor(authActionInterceptor)
                .addPathPatterns("/accounts/profile"); //拦截需要登录才可以使用的页面url

        super.addInterceptors(registry);
    }

}
