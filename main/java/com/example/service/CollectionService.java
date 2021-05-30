package com.example.service;

import com.example.entity.Collection;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.CollectionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CollectionService extends ServiceImpl<CollectionMapper, Collection> {

    @Resource
    private CollectionMapper collectionMapper;

}
