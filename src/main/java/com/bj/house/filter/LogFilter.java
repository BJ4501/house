package com.bj.house.filter;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;


/**
 * Created by BJ on 2018/1/11.
 */
public class LogFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(LogFilter.class);

    //在启动时进行
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    //在方法拦截的时候进行
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //logger.info("Req comming");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    //在容器销毁的时候进行
    @Override
    public void destroy() {

    }
}
