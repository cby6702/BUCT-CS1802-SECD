package com.cs1802.museum.service;

import com.cs1802.museum.bean.AllComments;
import com.cs1802.museum.bean.Comments;

import java.util.List;

public interface CommentsService {
    List<Comments> searchComments(int mid);

    Comments isComment(int uid, int mid);
    boolean judgeCommentable(int uid,int mid);

    List<AllComments> showComments(int mid);
    List<AllComments> showAllComments(int mid);

    boolean uploadComments(Comments comments);
}
