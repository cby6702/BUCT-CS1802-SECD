package com.cs1802.museum.service.impl;

import com.cs1802.museum.bean.Comments;
import com.cs1802.museum.mapper.CommentsMapper;
import com.cs1802.museum.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentsMapper commentsMapper;
    /*
    业务：根据博物馆id查询评价表
    逻辑：1.根据传入mid，查询comments表并返回对应comments对象
     */
    @Override
    public List<Comments> searchComments(int mid) {
        List<Comments> commentsList = commentsMapper.getComments(mid);
        return commentsList;
    }
}
