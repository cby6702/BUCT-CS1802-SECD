package com.cs1802.museum.controller;

import com.cs1802.museum.bean.AllComments;
import com.cs1802.museum.bean.Comments;
import com.cs1802.museum.bean.User;
import com.cs1802.museum.service.CommentsService;
import com.cs1802.museum.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper fastjson;

    /**
     * 功能：根据博物馆id查询评价表
     * url: /comments/search/{mid}
     * 返回comments对象（String）
     *
     * @param midString url上带的（前端传回来的都是string类型）
     * @return comments或null
     */
    @GetMapping("/search/{mid}")
    public String getComments(@PathVariable("mid") String midString) {
        //1.将前端传回的String类型mid转换为int类型
        int mid = Integer.parseInt(midString);
        //2.执行查询业务逻辑，使用List返回多个comments对象
        List<Comments> commentList = commentsService.searchComments(mid);
        try {
            return fastjson.writeValueAsString(commentList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能：判断用户是否能评价
     * url:/comments/judge/{uid}/{mid}
     * 返回true或false（String）
     *
     * @param uidString,midString url上带的（前端传回来的都是string类型）
     * @return true或false
     */
    @GetMapping("/judge/{uid}/{mid}")
    public boolean JudgeCommentable(@PathVariable("uid") String uidString,
                                    @PathVariable("mid") String midString) {
        //1.将前端传回的String类型uid、mid转换为int类型
        int uid = Integer.parseInt(uidString);
        int mid = Integer.parseInt(midString);
        //2.执行判断逻辑，查询users表和comments表
        return commentsService.judgeCommentable(uid,mid);
    }

    /**
     * 功能：根据博物馆id显示该博物馆所有用户评价及对应用户账号、头像
     * url:/comments/show/mid
     * 返回comments及对应用户账号、头像（String）
     *
     * @param  midString   url上带的（前端传回来的都是string类型）
     * @return comments及users对应信息合并的AllComments对象或null
     */
    @GetMapping("/show/{mid}")
    public String ShowComments(@PathVariable("mid") String midString) {
        //1.将前端传回的String类型mid转换为int类型
        int mid = Integer.parseInt(midString);
        List<AllComments> allcomments = commentsService.showAllComments(mid);
        //2.执行业务逻辑
        try {
            return fastjson.writeValueAsString(allcomments);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能：用户上传评价
     * url:/comments/upload
     * 返回true（上传成功）或false
     *
     * @param  json
     * @return true或false
     */
    @PostMapping("/upload")
    public boolean UploadComments(@RequestBody String json) {
        //1.解析json包
        try {
            Comments comments = fastjson.readValue(json, new TypeReference<Comments>() {
            });
            //2.执行业务逻辑，插入数据并返回是否成功
            return commentsService.uploadComments(comments);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
