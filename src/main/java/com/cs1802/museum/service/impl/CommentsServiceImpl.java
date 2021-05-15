package com.cs1802.museum.service.impl;

import com.cs1802.museum.bean.AllComments;
import com.cs1802.museum.bean.Comments;
import com.cs1802.museum.bean.User;
import com.cs1802.museum.mapper.CommentsMapper;
import com.cs1802.museum.service.CommentsService;
import com.cs1802.museum.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private  CommentsService commentsService;
    /*
    业务：根据博物馆id查询评价表
    逻辑：1.根据传入mid，查询comments表并返回对应comments对象
     */
    @Override
    public List<Comments> searchComments(int mid) {
        return commentsMapper.getComments(mid);
    }

    /*
    业务：查询用户是否已评分
    逻辑：1.根据传入uid、mid，查询comments表中是否有对应评分内容
     */
    @Override
    public Comments isComment(int uid,int mid) {
        return commentsMapper.isComment(uid,mid);
    }
    /*
        业务：判断用户是否可评分
        逻辑：1.根据传入uid、mid，判断用户是否被禁言或已评分
         */
    @Override
    public boolean judgeCommentable(int uid, int mid) {
        int isValidstate = userService.getValidstate(uid);
        Comments isComment = commentsService.isComment(uid, mid);
        //1.若用户被禁言或已评分，则返回false
        if (isValidstate == 0 || isComment != null)
            return false;
        //2.若用户未被禁言且未评分，则返回true
        else if (isValidstate == 1)
            return true;
        //3.其他情况返回false
        else return false;
    }

    /*
    业务：根据博物馆id返回该博物馆所有评价
    逻辑：1.查询comments表返回所有comments对象
     */
    @Override
    public List<AllComments> showComments(int mid) {
        return commentsMapper.showComments(mid);
    }
    /*
    业务：根据博物馆id返回该博物馆所有评价及对应用户信息
    逻辑：1.根据mid查询comments表返回所有comments对象
         2.根据uid查询users表返回用户账号、头像
         3.合并comments和用户账号、头像到Allcomments对象中并返回
     */
    @Override
    public List<AllComments> showAllComments(int mid) {
        //1.根据mid查询comments表返回所有comments对象到AllComments对象中
        List<AllComments> commentsList = commentsService.showComments(mid);
        //2.遍历commentsList
        for(int i=0;i<commentsList.size();i++){
            //3.获得uid，查询users表
            int uid = commentsList.get(i).getUid();
            User user=userService.getUser(uid);
            //4.获得用户账号、头像
            String UserAccount = user.getAccount();
            String UserAvatarUrl = user.getAvatarUrl();
            //5.将用户账号、头像插入Allcomments对象中
            commentsList.get(i).setAccount(UserAccount);
            commentsList.get(i).setAvatarUrl(UserAvatarUrl);
        }
        return commentsList;
    }
}
