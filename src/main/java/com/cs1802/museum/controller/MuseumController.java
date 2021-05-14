package com.cs1802.museum.controller;

import com.cs1802.museum.bean.Museum;
import com.cs1802.museum.bean.Page;
import com.cs1802.museum.service.MuseumService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/museum")
public class MuseumController {
    @Autowired
    private MuseumService museumService;
    @Autowired
    private ObjectMapper fastjson;

    /**
     * 功能：根据博物馆name查询博物馆表
     *      url: /museum/name
     *      返回museum对象（String）
     * @param  name   url上带的（前端传回来的都是string类型）
     * @return
     */
    @GetMapping("/search/{mid}")
    public String getMuseum(@PathVariable("mid") String name){
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
        int pageNo = Integer.parseInt(sortItemString);
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
}
