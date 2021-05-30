package com.example.service;

import com.example.entity.Museums;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.MuseumsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MuseumsService extends ServiceImpl<MuseumsMapper, Museums> {

    @Resource
    private MuseumsMapper museumsMapper;

}
