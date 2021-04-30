package com.cs1802.museum.service.impl;

import com.cs1802.museum.bean.User;
import com.cs1802.museum.mapper.UserMapper;
import com.cs1802.museum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /*
    业务：更新用户表的city字段返回更新后的user对象
    逻辑：1.根据传入uid，对users表对应的记录中的city字段进行修改
         2.修改若成功则返回true，获取新的user记录返回；否则返回null
     */
    @Override
    public User updateCity(String city, int uid) {
        if(userMapper.updateCity(city, uid)){
           return userMapper.getUser(uid);
        }
        return null;
    }
}
