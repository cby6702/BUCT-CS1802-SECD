package com.cs1802.museum.service;

import com.cs1802.museum.bean.Comments;
import com.cs1802.museum.bean.Texts;
import com.cs1802.museum.bean.User;
import java.util.List;

public interface UserService {
    User updateCity(String city, int uid);

    User getUser(int uid);


    int getValidstate(int uid);

    boolean Login(int uid, String password);

    boolean Sign(String name, String password,String email,String mobile);

    User updateUser(int uid, User user);

    List<Comments> MyComments(int uid);

    boolean deleteComments(int aid);

    boolean deleteAllComs(int uid);

    List<Texts> MyTexts(int uid);

    boolean deleteText(int tid);

    boolean deleteAllTexts(int uid);
}
