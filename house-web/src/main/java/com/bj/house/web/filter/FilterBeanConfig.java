package com.bj.house.web.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BJ on 2018/1/11.
 */
@Configuration
public class FilterBeanConfig {

    /**
     * 1.构造filter
     * 2.配置拦截urlPattern
     * 3.利用FilterRegistrationBean进行包装
     * @return
     */
    @Bean
    public FilterRegistrationBean logFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new LogFilter());
        List<String> urList = new ArrayList<>();
        urList.add("*");
        bean.setUrlPatterns(urList);
        return bean;
    }

}
