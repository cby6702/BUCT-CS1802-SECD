package com.cs1802.museum.controller;

import com.cs1802.museum.bean.Museum;
import com.cs1802.museum.service.MuseumService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
