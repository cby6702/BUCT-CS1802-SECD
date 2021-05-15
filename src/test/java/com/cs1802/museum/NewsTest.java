package com.cs1802.museum;

import com.cs1802.museum.bean.News;
import com.cs1802.museum.service.NewsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@DisplayName("NewsTest")
public class NewsTest {
    @Autowired
    private NewsService newsService;

//    @Test
//    @Transactional
//    @DisplayName("根据mid查询新闻表")
//    public void getNews() {
//        List<News> newsList = newsService.searchNews(1);
//        System.out.println(newsList);
//    }
}
