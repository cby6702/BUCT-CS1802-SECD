package com.cs1802.museum.service;

import com.cs1802.museum.bean.User;

public interface UserService {
    User updateCity(String city, String account);
    User getUser(String account);
    boolean Login(String account, String password);
}
