package com.cs1802.museum.service;

import com.cs1802.museum.bean.Comments;

import java.util.List;

public interface CommentsService {
    List<Comments> searchComments(int mid);
}
