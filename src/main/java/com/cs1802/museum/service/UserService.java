package com.cs1802.museum.service;

import com.cs1802.museum.bean.User;

public interface UserService {
    User updateCity(String city, int uid);
    User getUser(int uid);
}
