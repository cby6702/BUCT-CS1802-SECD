package com.example.controller;

import com.example.common.Result;
import com.example.entity.Text;
import com.example.service.TextService;
import com.example.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import com.example.exception.CustomException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/text")
public class TextController {
    @Resource
    private TextService textService;
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
    public Result<?> save(@RequestBody Text text) {
        return Result.success(textService.save(text));
    }

    @PutMapping
    public Result<?> update(@RequestBody Text text) {
        return Result.success(textService.updateById(text));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        textService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(textService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(textService.list());
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Text> query = Wrappers.<Text>lambdaQuery().like(Text::getComtext, name).orderByDesc(Text::getTid);;
        return Result.success(textService.page(new Page<>(pageNum, pageSize), query));
    }

}
