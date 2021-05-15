package com.cs1802.museum.mapper;

import com.cs1802.museum.bean.Comments;
import com.cs1802.museum.bean.Texts;
import com.cs1802.museum.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    boolean updateCity(String city, int uid);
    User getUser(int uid);

    int getValidstate(int uid);

    User getSignUser(String name);
    boolean addUser(String name,String password);
    boolean updateUser(int uid,String name,String gender,String birthday,
                    String email,String mobile,String avatarUrl);
    List<Comments> MyComments(int uid);
    boolean deleteComments(int aid);
    boolean deleteAllComs(int uid);
    List<Texts> MyTexts(int uid);
    boolean deleteText(int tid);
    boolean deleteAllTexts(int uid);
}
