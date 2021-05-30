package com.example.controller;

import com.example.common.Result;
import com.example.entity.Museums;
import com.example.service.MuseumsService;
import com.example.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import com.example.exception.CustomException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/museums")
public class MuseumsController {
    @Resource
    private MuseumsService museumsService;
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
    public Result<?> save(@RequestBody Museums museums) {
        return Result.success(museumsService.save(museums));
    }

    @PutMapping
    public Result<?> update(@RequestBody Museums museums) {
        return Result.success(museumsService.updateById(museums));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        museumsService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(museumsService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(museumsService.list());
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Museums> query = Wrappers.<Museums>lambdaQuery().like(Museums::getName, name).orderByDesc(Museums::getMid);;
        return Result.success(museumsService.page(new Page<>(pageNum, pageSize), query));
    }

}
