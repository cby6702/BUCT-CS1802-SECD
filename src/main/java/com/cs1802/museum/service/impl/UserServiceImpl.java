package com.cs1802.museum.service.impl;

import com.cs1802.museum.bean.Comments;
import com.cs1802.museum.bean.Texts;
import com.cs1802.museum.bean.User;
import com.cs1802.museum.mapper.UserMapper;
import com.cs1802.museum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    业务：通过uid和passwoord登录
    逻辑：根据传入的uid查找该用户是否存在，如果存在，检验password是否正确，正确返回true；不正确返回false；
									    如果不存在，返回false。
     */

    @Override
    public boolean Login(int uid,String password){
        User user = userMapper.getUser(uid);
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


    /*
    业务：通过name和passwoord注册
    逻辑：根据传入的name查找该用户是否存在，如果不存在，在users表中插入该用户信息，插入成功，则返回true；
                                     如果用户存在或者插入失败返回false；
     */

    @Override
    public boolean Sign(String name,String password){
        User user=userMapper.getSignUser(name);
        System.out.println("user=" + user);
        if(user==null){
            return userMapper.addUser(name,password);
        }
        else
            return false;
    }

    /*
    业务：更新用户表的name,gender,birthday,email,mobile,avatarUrl字段返回更新后的user对象
    逻辑：1.根据传入uid，对users表对应的记录中的name,gender,birthday,email,mobile,avatarUrl字段进行修改
         2.修改若成功则返回true，获取新的user记录返回；否则返回null
     */
    @Override
    public User updateUser(int uid,User user){
        if(userMapper.updateUser(uid, user.getName(),user.getGender(),user.getBirthday(),
                                    user.getEmail(),user.getMobile(),user.getAvatarUrl())){
            return userMapper.getUser(uid);
        }
        return null;
    }

    /*
    业务：根据uid查询comments表
    逻辑：根据传入uid，查询comments表并返回comments对象
     */

    @Override
    public List<Comments> MyComments(int uid){
        List<Comments> MycommentsList= userMapper.MyComments(uid);
        return MycommentsList;
    }

    /*
    业务：根据aid查询comments表并删除
    逻辑：根据传入aid，查询comments表并将对应的评论删除
     */

    @Override
    public boolean deleteComments(int aid){
        return userMapper.deleteComments(aid);
    }

    /*
    业务：根据uid查询comments表并删除
    逻辑：根据传入uid，查询comments表并将对应的评论删除
     */

    @Override
    public boolean deleteAllComs(int uid){
        return userMapper.deleteAllComs(uid);
    }

    /*
    业务：根据uid查询text表
    逻辑：根据传入uid，查询text表并返回texts对象
     */

    @Override
    public List<Texts> MyTexts(int uid){
        List<Texts> MyTextsList= userMapper.MyTexts(uid);
        return MyTextsList;
    }

    /*
    业务：根据tid查询text表并删除
    逻辑：根据传入tid，查询text表并将对应的消息删除
     */

    @Override
    public boolean deleteText(int tid){
        return userMapper.deleteText(tid);
    }

    /*
    业务：根据uid查询text表并删除
    逻辑：根据传入uid，查询text表并将对应的消息删除
     */

    @Override
    public boolean deleteAllTexts(int uid){
        return userMapper.deleteAllTexts(uid);
    }
}
