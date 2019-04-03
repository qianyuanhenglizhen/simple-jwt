package com.han.config;

import com.han.handler.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author jjw
 * @title: InterceptorConfig
 * @projectName simple-jwt
 * @description: 拦截器配置类
 * @date 2019/4/2 15:40
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    /**
     * 拦截路径
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor(){
        return new AuthenticationInterceptor();
    }
}
