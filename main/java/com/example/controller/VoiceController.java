package com.example.controller;

import com.example.common.Result;
import com.example.entity.Voice;
import com.example.service.VoiceService;
import com.example.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import com.example.exception.CustomException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/voice")
public class VoiceController {
    @Resource
    private VoiceService voiceService;
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
    public Result<?> save(@RequestBody Voice voice) {
        return Result.success(voiceService.save(voice));
    }

    @PutMapping
    public Result<?> update(@RequestBody Voice voice) {
        return Result.success(voiceService.updateById(voice));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        voiceService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(voiceService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(voiceService.list());
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Voice> query = Wrappers.<Voice>lambdaQuery().like(Voice::getVoice, name).orderByDesc(Voice::getVid);;
        return Result.success(voiceService.page(new Page<>(pageNum, pageSize), query));
    }

}
