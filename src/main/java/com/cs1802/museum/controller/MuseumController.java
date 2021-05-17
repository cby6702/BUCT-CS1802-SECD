package com.cs1802.museum.controller;

import com.cs1802.museum.bean.Comments;
import com.cs1802.museum.bean.Museum;
import com.cs1802.museum.bean.Page;
import com.cs1802.museum.bean.Score;
import com.cs1802.museum.service.MuseumService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/museum")
public class MuseumController<T> {
    @Autowired
    private MuseumService museumService;
    @Autowired
    private ObjectMapper fastjson;

    /**
     * 功能：根据博物馆name查询博物馆表
     *      url: /museum/search/name
     *      返回museum对象（String）
     * @param  name   url上带的（前端传回来的都是string类型）
     * @return
     */
    @GetMapping("/searchName/{name}")
    public String getMuseum(@PathVariable("name") String name){
        //1.执行查询业务逻辑
        Museum museum = museumService.searchMuseum(name);
        try {
            return fastjson.writeValueAsString(museum);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能：首页根据city字段查询博物馆表，得到分页数据
     *      url: /museum/show/city/pageNo
     * @param city      url上带的
     * @param pageNoString      url上带的
     * @return      Page<Museum>或null
     */
    @GetMapping("/show/{city}/{pageNo}")
    public String getMuseumList(@PathVariable("city") String city,
                                @PathVariable("pageNo")String pageNoString){
        //1.将传入的pageNo从String转为int
        int pageNo = Integer.parseInt(pageNoString);
        //2.分页查询museums表得到分页数据
        Page<Museum> page = museumService.showPage(city, pageNo);
        try {
            return fastjson.writeValueAsString(page);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * url:/sort/{city}/{sortItem}/{sortKind}/{pageNo}
     * 排名页显示分页数据
     * @param city
     * @param sortItemString
     * @param sortKindString
     * @param pageNoString
     * @return
     */
    @GetMapping("/sort/{city}/{sortItem}/{sortKind}/{pageNo}")
    public String sortMuseum(@PathVariable("city") String city,
                             @PathVariable("sortItem") String sortItemString,
                             @PathVariable("sortKind") String sortKindString,
                             @PathVariable("pageNo") String pageNoString){
        //1.将pageNoString和sortItemString,sortKindString转为int
        int pageNo = Integer.parseInt(pageNoString);
        int sortItem = Integer.parseInt(sortItemString);
        int sortKind = Integer.parseInt(sortKindString);
        //2.分页根据条件查询博物馆排名
        Page<Museum> page = museumService.sortMuseum(city, sortItem, sortKind, pageNo);
        try {
            return fastjson.writeValueAsString(page);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能：根据博物馆id查询博物馆表
     *      url: /museum/searchId/mid
     *      返回museum对象（String）
     * @param  midString   url上带的（前端传回来的都是string类型）
     * @return
     */
    @GetMapping("/searchId/{mid}")
    public String getMuseum1(@PathVariable("mid") String midString) {
        //1.将mid转为int类型
        int mid = Integer.parseInt(midString);
        //2.执行查询业务逻辑
        Museum museum = museumService.searchMuseum1(mid);
        try {
            return fastjson.writeValueAsString(museum);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 查询页查询功能
     * @param searchKind
     * @param searchItem
     * @return
     */
    @GetMapping("/search/{searchKind}/{searchItem}")
    public String search(@PathVariable("searchKind") String searchKind,
                         @PathVariable("searchItem") String searchItem){
        List<T> list = museumService.search(searchKind, searchItem);
        try {
            return fastjson.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能：根据博物馆id查询博物馆表中对应评分
     * url: /museum/showScore/{mid}
     * 返回museums表中对应评分（String）
     *
     * @param midString url上带的（前端传回来的都是string类型）
     * @return Score或null
     */
    @GetMapping("/showScore/{mid}")
    public String showScore(@PathVariable("mid") String midString) {
        //1.将前端传回的String类型mid转换为int类型
        int mid = Integer.parseInt(midString);
        //2.执行业务逻辑，返回Score对象
        Score score= museumService.getScore(mid);
        try {
            return fastjson.writeValueAsString(score);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
