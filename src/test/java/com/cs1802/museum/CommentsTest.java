package com.cs1802.museum;

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

    @Test
    @Transactional
    @DisplayName("根据mid查询评价表test")
    public void getCommentsTest() {
        List<Comments> commentsList = commentsService.searchComments(1);
        System.out.println(commentsList);
    }
}
