package com.cs1802.museum.controller;

import com.cs1802.museum.bean._Collection;
import com.cs1802.museum.service.CollectionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;
    @Autowired
    private ObjectMapper fastjson;

    /**
     * 功能：根据博物馆id查询藏品表
     *      url: /collection/mid
     *      返回collection对象（String）
     * @param  midString   url上带的（前端传回来的都是string类型）
     * @return  collection或null
     */

    @GetMapping("/search/{mid}")
    public String getCollection(@PathVariable("mid") String midString) {
        //1.将前端传回的String类型mid转换为int类型
        int mid = Integer.parseInt(midString);
        //2.执行查询业务逻辑，使用List返回多个collection对象
        List<_Collection> collectionList = collectionService.searchCollection(mid);
        try{
            return fastjson.writeValueAsString(collectionList);
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }
}


