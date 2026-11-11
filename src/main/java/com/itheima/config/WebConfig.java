package com.itheima.config;

import com.itheima.interceptor.DemoInterceptor;
import com.itheima.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
   // @Autowired
   //private DemoInterceptor demoInterceptor;
    @Autowired
    private TokenInterceptor TokenInterceptor;
    @Override
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
        registry.addInterceptor(TokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login");
    }
}
