package com.example.controller;

import com.example.common.Result;
import com.example.entity.Comments;
import com.example.service.CommentsService;
import com.example.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import com.example.exception.CustomException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {
    @Resource
    private CommentsService commentsService;
    @Resource
    private HttpServletRequest request;

    public User getUser() {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("-1", "请登录");
        }
        return user;
    }

    @PostMapping
    public Result<?> save(@RequestBody Comments comments) {
        return Result.success(commentsService.save(comments));
    }

    @PutMapping
    public Result<?> update(@RequestBody Comments comments) {
        return Result.success(commentsService.updateById(comments));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        commentsService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(commentsService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(commentsService.list());
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Comments> query = Wrappers.<Comments>lambdaQuery().like(Comments::getComment, name).orderByDesc(Comments::getAid);;
        return Result.success(commentsService.page(new Page<>(pageNum, pageSize), query));
    }

}
