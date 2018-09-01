package com.yxw.service.impl;

import com.yxw.dao.UsersMapper;
import com.yxw.pojo.Users;
import com.yxw.service.UserService;
import com.yxw.utils.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Yan on 2018/8/30.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;
    /**
     * 根据用户名和密码查找用户
     * @param userCode
     * @param password
     * @return
     */
    @Override
    public E3Result findUserByNamePass(String userCode, String password) {
        Users user = usersMapper.selectByNamePass(userCode,password);
        if(null==user){
           return E3Result.build(400,"无此用户");
        }else{
            user.setLastLogintime(new Date());
            updateUser(user);
            return E3Result.ok();
        }
    }

    @Override
    public E3Result addUser(Users user) {
        usersMapper.insertSelective(user);
        return E3Result.ok();
    }

    @Override
    public void updateUser(Users user) {
        usersMapper.updateByPrimaryKeySelective(user);
    }
}
