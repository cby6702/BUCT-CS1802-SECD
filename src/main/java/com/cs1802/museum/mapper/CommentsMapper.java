package com.cs1802.museum.mapper;

import com.cs1802.museum.bean.Comments;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentsMapper {
    List<Comments> getComments(int mid);

}
