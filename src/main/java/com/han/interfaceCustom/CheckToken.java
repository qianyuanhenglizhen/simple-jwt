package com.han.interfaceCustom;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jjw
 * @title: CheckToken
 * @projectName simple-jwt
 * @description: TODO
 * @date 2019/4/2 13:25
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckToken {
    boolean required() default true;
}
