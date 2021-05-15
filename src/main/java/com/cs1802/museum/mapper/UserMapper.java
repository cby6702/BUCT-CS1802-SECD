package com.cs1802.museum.mapper;

import com.cs1802.museum.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    boolean updateCity(String city, int uid);
    User getUser(int uid);
    User AccountExists(String account);
}
