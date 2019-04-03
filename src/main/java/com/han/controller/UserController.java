package com.han.controller;

import com.alibaba.fastjson.JSONObject;
import com.han.entity.User;
import com.han.interfaceCustom.CheckToken;
import com.han.interfaceCustom.LoginToken;
import com.han.service.UserService;
import com.han.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author jjw
 * @title: UserController
 * @projectName simple-jwt
 * @description: TODO
 * @date 2019/4/2 15:43
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param user
     * @return
     */
    @LoginToken
    @PostMapping("/login")
    public Object login(@RequestBody @Valid User user){
        JSONObject jsonObject=new JSONObject();
        User userForBase=userService.selectUserByUserName(user);
        if (userForBase == null) {
            jsonObject.put("message", "登录失败,用户不存在");
            return jsonObject;
        } else {
            if (!userForBase.getPassword().equals(user.getPassword())) {
                jsonObject.put("message", "登录失败,密码错误");
                return jsonObject;
            } else {
                String token = JwtUtil.createJWT(6000000, userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        }
    }
    //查看个人信息
    @CheckToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "你已通过验证";
    }
}
