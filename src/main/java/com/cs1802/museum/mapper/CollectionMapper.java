package com.cs1802.museum.mapper;

import com.cs1802.museum.bean._Collection;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CollectionMapper {
    List<_Collection> getCollection(int mid);
}
