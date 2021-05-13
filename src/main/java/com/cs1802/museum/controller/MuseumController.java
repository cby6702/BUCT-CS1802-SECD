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
     * 功能：根据博物馆id查询博物馆表
     *      url: /museum/mid
     *      返回museum对象（String）
     * @param  midString   url上带的（前端传回来的都是string类型）
     * @return
     */
    @GetMapping("/search/{mid}")
    public String getMuseum(@PathVariable("mid") String midString){
        //1.将前端传回的String类型mid转换为int类型
        int mid = Integer.parseInt(midString);
        //2.执行查询业务逻辑
        Museum museum = museumService.searchMuseum(mid);
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
}
