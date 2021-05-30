package com.example.service;

import com.example.entity.Voice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.VoiceMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class VoiceService extends ServiceImpl<VoiceMapper, Voice> {

    @Resource
    private VoiceMapper voiceMapper;

}
