package com.cs1802.museum;

import com.cs1802.museum.bean.AllComments;
import com.cs1802.museum.bean.Comments;
import com.cs1802.museum.service.CommentsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@DisplayName("CommentsTest")
public class CommentsTest {
    @Autowired
    private CommentsService commentsService;
//
//
//    @Test
//    @Transactional
//    @DisplayName("根据mid查询评价表test")
//    public void getCommentsTest() {
//        List<Comments> commentsList = commentsService.searchComments(1);
//        System.out.println(commentsList);
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("判断用户是否可评分test")
//    public void judgeCommentableTest() {
//        boolean t = commentsService.judgeCommentable(1,2);
//        System.out.println(t);
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("根据mid显示该博物馆所有评价test")
//    public void showCommentsTest() {
//        List<AllComments> comments = commentsService.showAllComments(1);
//        System.out.println(comments);
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("用户上传评价test")
//    public void uploadCommentsTest() {
//        Comments comments = new Comments();
//        comments.setUid(0);
//        comments.setMid(3);
//        comments.setExhibitionstar(3);
//        comments.setServicestar(3);
//        comments.setEnvironmentstar(3);
//        comments.setGeneral_comment(3);
//        //comments.setComment("好");
//        //comments.setPicture("……");
//        boolean t = commentsService.uploadComments(comments);
//        System.out.println(t);
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("根据mid查询评价表test")
//    public void getCommentsTest() {
//        List<Comments> commentsList = commentsService.searchComments(1);
//        System.out.println(commentsList);
//    }
}
