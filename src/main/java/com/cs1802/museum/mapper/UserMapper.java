package com.cs1802.museum.mapper;

import com.cs1802.museum.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    boolean updateCity(String city, String account);
    User getUser(String account);
    User AccountExists(String account);
}
