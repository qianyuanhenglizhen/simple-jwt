package com.han.entity;

/**
 * @author jjw
 * @title: User
 * @projectName simple-jwt
 * @description: TODO
 * @date 2019/4/2 13:48
 */
public class User {

    private Integer id;
    private String userName;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
