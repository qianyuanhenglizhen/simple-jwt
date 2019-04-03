package com.han.service;

import com.han.entity.User;

/**
 * @author jjw
 * @title: UserService
 * @projectName simple-jwt
 * @description: TODO
 * @date 2019/4/2 15:03
 */

public interface UserService {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUserByUserName(User user);
}
