package com.cs1802.museum.mapper;

import com.cs1802.museum.bean.Museum;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MuseumMapper {
    Museum getMuseum(int mid);
}
