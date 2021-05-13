package com.cs1802.museum.controller;

import com.cs1802.museum.bean.Comments;
import com.cs1802.museum.bean._Collection;
import com.cs1802.museum.service.CommentsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private ObjectMapper fastjson;

    /**
     * 功能：根据博物馆id查询评价表
     *      url: /comments/mid
     *      返回comments对象（String）
     * @param  midString   url上带的（前端传回来的都是string类型）
     * @return  comments或null
     */

    @GetMapping("/search/{mid}")
    public String getComments(@PathVariable("mid") String midString) {
        //1.将前端传回的String类型mid转换为int类型
        int mid = Integer.parseInt(midString);
        //2.执行查询业务逻辑，使用List返回多个collection对象
        List<Comments> commentList = commentsService.searchComments(mid);
        try{
            return fastjson.writeValueAsString(commentList);
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }
}
