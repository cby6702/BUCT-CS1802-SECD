package com.cs1802.museum.service.impl;

import com.cs1802.museum.bean.News;
import com.cs1802.museum.mapper.NewsMapper;
import com.cs1802.museum.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;
    /*
    业务：根据博物馆id查询新闻表
    逻辑：1.根据传入mid，查询news表并返回对应news对象
     */
    @Override
    public List<News> searchNews(int mid) {
        return newsMapper.getNews(mid);
    }
}
