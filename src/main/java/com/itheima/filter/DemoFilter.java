package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebFilter("/*")
@Slf4j
public class DemoFilter implements Filter {

    @Override
    //初始化方法 web服务器启动时执行，仅执行一次
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化方法运行了");
        Filter.super.init(filterConfig);
    }

    @Override
    //拦截到请求，执行拦截操作 会执行多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("拦截到请求，执行拦截操作");
    }

    @Override
    //销毁方法 web服务器关闭时执行，仅执行一次
    public void destroy() {
        log.info("销毁方法运行了");
        Filter.super.destroy();
    }



}
