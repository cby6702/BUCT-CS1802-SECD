package com.example.controller;

import com.example.common.Result;
import com.example.entity.Collection;
import com.example.service.CollectionService;
import com.example.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import com.example.exception.CustomException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/collection")
public class CollectionController {
    @Resource
    private CollectionService collectionService;
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
    public Result<?> save(@RequestBody Collection collection) {
        return Result.success(collectionService.save(collection));
    }

    @PutMapping
    public Result<?> update(@RequestBody Collection collection) {
        return Result.success(collectionService.updateById(collection));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        collectionService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(collectionService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(collectionService.list());
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Collection> query = Wrappers.<Collection>lambdaQuery().like(Collection::getCname, name).orderByDesc(Collection::getCid);;
        return Result.success(collectionService.page(new Page<>(pageNum, pageSize), query));
    }

}
