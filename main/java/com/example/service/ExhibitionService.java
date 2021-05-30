package com.example.service;

import com.example.entity.Exhibition;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.ExhibitionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ExhibitionService extends ServiceImpl<ExhibitionMapper, Exhibition> {

    @Resource
    private ExhibitionMapper exhibitionMapper;

}
