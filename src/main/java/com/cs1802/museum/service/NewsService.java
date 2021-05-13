package com.cs1802.museum.service;

import com.cs1802.museum.bean.News;

import java.util.List;

public interface NewsService {
    List<News> searchNews(int mid);
}
