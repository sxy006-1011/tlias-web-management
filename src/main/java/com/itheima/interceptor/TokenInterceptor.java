package com.itheima.interceptor;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component

public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求路径
        //String path = request.getRequestURI();
        //判断是不是登录操作
      //  if (path.contains("/login")) {
        //    log.info("登录请求，放行");
       //     return true;
        //}
        //获取请求头
     String token = request.getHeader("token");
       // if (token == null || token.isEmpty()) {
        //    log.info("请求头中没有token，拦截");
       //     response.setStatus(401);
        //    return false;
      //  }
        //校验令牌
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("token校验失败，拦截");
            response.setStatus(401);
            return false;
        }
        log.info("token校验通过，放行");
        return true;
    }
}


