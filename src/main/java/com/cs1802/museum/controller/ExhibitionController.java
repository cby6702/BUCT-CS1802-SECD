package com.cs1802.museum.controller;

import com.cs1802.museum.bean.Exhibition;
import com.cs1802.museum.service.ExhibitionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exhibition")
public class ExhibitionController {
    @Autowired
    private ExhibitionService exhibitionService;
    @Autowired
    private ObjectMapper fastjson;

    /**
     * 功能：根据博物馆id查询展览表
     * url: /exhibition/search/mid
     * 返回exhibition对象（String）
     *
     * @param midString url上带的（前端传回来的都是string类型）
     * @return exhibition或null
     */

    @GetMapping("/search/{mid}")
    public String getExhibition(@PathVariable("mid") String midString) {
        //1.将前端传回的String类型mid转换为int类型
        int mid = Integer.parseInt(midString);
        //2.执行查询业务逻辑，使用List返回多个exhibition对象
        List<Exhibition> exhibitionList = exhibitionService.searchExhibition(mid);
        try {
            return fastjson.writeValueAsString(exhibitionList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
