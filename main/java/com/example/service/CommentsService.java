package com.example.service;

import com.example.entity.Comments;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.CommentsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommentsService extends ServiceImpl<CommentsMapper, Comments> {

    @Resource
    private CommentsMapper commentsMapper;

}
