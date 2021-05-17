package com.cs1802.museum.service;

import com.cs1802.museum.bean.News;
import com.cs1802.museum.bean.Page;

import java.util.List;

public interface NewsService {
    List<News> searchNews(int mid);

    Page<News> showPage(int pageNo);
}
