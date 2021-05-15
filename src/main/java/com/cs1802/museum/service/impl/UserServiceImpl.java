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
        if(userMapper.updateCity(city,uid)){
           return userMapper.getUser(uid);
        }
        return null;
    }
    /*
    业务：通过uid查找用户信息返回user对象
    逻辑：根据传入uid，返回相应user
     */
    @Override
    public User getUser(int uid){
        return  userMapper.getUser(uid);
    }

    /*
<<<<<<< Updated upstream
    业务：通过account和passwoord登录
    逻辑：根据传入的account查找该用户是否存在，如果存在，检验password是否正确，正确返回true；不正确返回false；
									    如果不存在，返回false。
     */

    @Override
    public boolean Login(String account,String password){
        User user = userMapper.AccountExists(account);
        System.out.println("user=" + user);
        if(user==null)
            return false;
        else {
            if(user.getPassword().equals(password))
                return true;
            else
                return false;
        }
    }
    /*
    业务：通过uid查找users表中validstate判断用户是否被禁言
    逻辑：根据传入uid，返回validstate
     */
    @Override
    public int getValidstate(int uid) {
        return userMapper.getValidstate(uid);
    }


}
