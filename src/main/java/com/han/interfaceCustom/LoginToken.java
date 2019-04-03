package com.han.interfaceCustom;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jjw
 * @title: LoginToken
 * @projectName simple-jwt
 * @description: TODO
 * @date 2019/4/2 13:22
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginToken {
    boolean required() default true;
}
