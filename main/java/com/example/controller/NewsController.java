package com.example.controller;

import com.example.common.Result;
import com.example.entity.News;
import com.example.service.NewsService;
import com.example.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import com.example.exception.CustomException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    @Resource
    private NewsService newsService;
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
    public Result<?> save(@RequestBody News news) {
        return Result.success(newsService.save(news));
    }

    @PutMapping
    public Result<?> update(@RequestBody News news) {
        return Result.success(newsService.updateById(news));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        newsService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(newsService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(newsService.list());
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<News> query = Wrappers.<News>lambdaQuery().like(News::getTitle, name).orderByDesc(News::getNid);;
        return Result.success(newsService.page(new Page<>(pageNum, pageSize), query));
    }

}
