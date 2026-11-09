package com.itheima.filter;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1. 获取请求路径
        String requestURI = request.getRequestURI();
        //2.判断是否是登录请求，是则放行
        if (requestURI.contains("/login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            log.info("登录请求，放行");
            return;
        }
        //3.获取请求头中的token
        String token = request.getHeader("token");
        //4.判断token是否存在，如果不存在，说明用户没有登陆，返回错误信息（相应401）状态码
        if (token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            log.info("token不存在，响应401");
            return;
        }
        //5.若token存在，校验令牌，如果校验失败，返回错误信息
        try {
            JwtUtils.parseJWT(token);
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            log.info("token不存在，响应401");
            return;
        }
        //6.校验通过，放行
        log.info("token校验通过，放行");
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
