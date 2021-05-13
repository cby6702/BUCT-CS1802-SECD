package com.cs1802.museum;

import com.cs1802.museum.bean.Exhibition;
import com.cs1802.museum.service.ExhibitionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@DisplayName("ExhibitionTest")
public class ExhibitionTest {
    @Autowired
    private ExhibitionService exhibitionService;

    @Test
    @Transactional
    @DisplayName("根据mid查询展览表Test")
    public void getExhibition(){
        List<Exhibition> exhibitionList = exhibitionService.searchExhibition(1);
        System.out.println(exhibitionList);
    }
}
