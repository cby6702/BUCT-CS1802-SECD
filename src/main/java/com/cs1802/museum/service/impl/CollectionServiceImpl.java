package com.cs1802.museum.service.impl;

import com.cs1802.museum.bean._Collection;
import com.cs1802.museum.mapper.CollectionMapper;
import com.cs1802.museum.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionMapper collectionMapper;
    /*
    业务：根据博物馆id查询藏品表
    逻辑：1.根据传入mid，查询collection表并返回对应collection对象
     */
    @Override
    public List<_Collection> searchCollection(int mid) {
        return collectionMapper.getCollection(mid);
    }
}
