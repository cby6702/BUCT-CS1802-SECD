package com.example.controller;

import com.example.common.Result;
import com.example.entity.Exhibition;
import com.example.service.ExhibitionService;
import com.example.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import com.example.exception.CustomException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/exhibition")
public class ExhibitionController {
    @Resource
    private ExhibitionService exhibitionService;
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
    public Result<?> save(@RequestBody Exhibition exhibition) {
        return Result.success(exhibitionService.save(exhibition));
    }

    @PutMapping
    public Result<?> update(@RequestBody Exhibition exhibition) {
        return Result.success(exhibitionService.updateById(exhibition));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        exhibitionService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(exhibitionService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(exhibitionService.list());
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Exhibition> query = Wrappers.<Exhibition>lambdaQuery().like(Exhibition::getName, name).orderByDesc(Exhibition::getEid);;
        return Result.success(exhibitionService.page(new Page<>(pageNum, pageSize), query));
    }

}
