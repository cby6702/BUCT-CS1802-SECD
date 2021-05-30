package com.example.service;

import com.example.entity.News;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.NewsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NewsService extends ServiceImpl<NewsMapper, News> {

    @Resource
    private NewsMapper newsMapper;

}
