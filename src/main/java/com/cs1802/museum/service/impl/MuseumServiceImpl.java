package com.cs1802.museum.service.impl;

import com.cs1802.museum.bean.Museum;
import com.cs1802.museum.mapper.MuseumMapper;
import com.cs1802.museum.service.MuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MuseumServiceImpl implements MuseumService {
    @Autowired
    private MuseumMapper museumMapper;
    /*
    业务：根据博物馆id查询博物馆表
    逻辑：1.根据传入mid，查询museum表对应的所有信息并返回
     */
    @Override
    public Museum searchMuseum(int mid) {
        return museumMapper.getMuseum(mid);
    }
}
