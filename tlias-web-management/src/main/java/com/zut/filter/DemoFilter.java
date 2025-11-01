package com.zut.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
@Slf4j
//@WebFilter(urlPatterns = "/*")//拦截所有请求
public class DemoFilter implements Filter {
    //初始化方法，只调用一次，filter实例创建之后调用
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       log.info("初始化请求 init.........");
    }

    //销毁方法，只调用一次，filter实例销毁之前调用
    @Override
    public void destroy() {
        log.info("销毁filter： destroy.........");
    }
//过滤方法，每次请求都会调用
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    log.info("拦截请求 doFilter.........");
    //校验令牌 通过之后放行

    //放行
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
