package com.cs1802.museum.mapper;

import com.cs1802.museum.bean.Exhibition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface ExhibitionMapper {
    List<Exhibition> getExhibition(int mid);
}
