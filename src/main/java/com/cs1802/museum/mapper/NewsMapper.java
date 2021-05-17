package com.cs1802.museum.mapper;

import com.cs1802.museum.bean.News;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NewsMapper {
    List<News> getNews(int mid);

    int getCount();

    List<News> getItems(int begin, int pageSize);
}
