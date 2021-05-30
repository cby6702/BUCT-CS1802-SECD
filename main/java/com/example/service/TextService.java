package com.example.service;

import com.example.entity.Text;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.TextMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TextService extends ServiceImpl<TextMapper, Text> {

    @Resource
    private TextMapper textMapper;

}
