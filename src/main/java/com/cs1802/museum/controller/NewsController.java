package com.cs1802.museum.controller;

import com.cs1802.museum.bean.News;
import com.cs1802.museum.service.NewsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private ObjectMapper fastjson;

    /**
     * 功能：根据博物馆id查询新闻表
     *      url: /news/mid
     *      返回news对象（String）
     * @param  midString   url上带的（前端传回来的都是string类型）
     * @return  news或null
     */

    @GetMapping("/search/{mid}")
    public String getNews(@PathVariable("mid") String midString) {
        //1.将前端传回的String类型mid转换为int类型
        int mid = Integer.parseInt(midString);
        //2.执行查询业务逻辑，使用List返回多个collection对象
        List<News> newsList = newsService.searchNews(mid);
        try{
            return fastjson.writeValueAsString(newsList);
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }
}
