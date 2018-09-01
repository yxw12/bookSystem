package com.yxw.service;

import com.yxw.pojo.Users;
import com.yxw.utils.E3Result;

/**
 * Created by Yan on 2018/8/30.
 */
public interface UserService {
    E3Result findUserByNamePass(String userCode, String password);

    E3Result addUser(Users user);

    void updateUser(Users user);
}
