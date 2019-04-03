package com.han.handler;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.han.entity.User;
import com.han.interfaceCustom.CheckToken;
import com.han.interfaceCustom.LoginToken;
import com.han.service.UserService;
import com.han.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author jjw
 * @title: AuthenticationInterceptor
 * @projectName simple-jwt
 * @description: 自定义拦截器：访问资源时是否已登录
 * @date 2019/4/2 13:59
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //String token = request.getHeader("token");
        String token = request.getParameter("token");
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //检查是否有LoginToken注释，有则跳过认证
        if (method.isAnnotationPresent(LoginToken.class)) {
            LoginToken loginToken = method.getAnnotation(LoginToken.class);
            if (loginToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(CheckToken.class)) {
            CheckToken checkToken = method.getAnnotation(CheckToken.class);
            if (checkToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 user id
                Integer userId;
                try {
                   // userId = JWT.decode(token).getClaim("id").asString();
                    userId =JWT.decode(token).getClaims().get("id").asInt();
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("访问异常！");
                }
                User user = userService.selectByPrimaryKey(userId);
                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                Boolean verify = JwtUtil.isVerify(token, user);
                if (!verify) {
                    throw new RuntimeException("非法访问！");
                }
                return true;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
