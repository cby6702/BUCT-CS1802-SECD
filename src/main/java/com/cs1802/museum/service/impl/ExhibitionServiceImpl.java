package com.cs1802.museum.service.impl;

import com.cs1802.museum.bean.Exhibition;
import com.cs1802.museum.mapper.ExhibitionMapper;
import com.cs1802.museum.service.ExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExhibitionServiceImpl implements ExhibitionService {
    @Autowired
    private ExhibitionMapper exhibitionMapper;
    /*
    业务：根据博物馆id查询展览表
    逻辑：1.根据传入mid，查询exhibition表并返回exhibition对象
     */
    @Override
    public List<Exhibition> searchExhibition(int mid){
        return exhibitionMapper.getExhibition(mid);
    }
}
