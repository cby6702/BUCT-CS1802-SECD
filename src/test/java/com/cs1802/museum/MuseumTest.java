package com.cs1802.museum;

import com.cs1802.museum.bean.Museum;
import com.cs1802.museum.bean.Page;
import com.cs1802.museum.service.MuseumService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@DisplayName("MuseumTest")
public class MuseumTest {
    @Autowired
    private MuseumService museumService;

    @Test
    @Transactional
    @DisplayName("根据name查询博物馆表test")
    public void getMuseumTest(){
        Museum museum = museumService.searchMuseum("北京博物馆");
        System.out.println(museum);
    }

    @Test
    @Transactional
    @DisplayName("博物馆排名")
    public void sortMuseumTest(){
        Page<Museum> page = museumService.sortMuseum("all", 0, 1, 1);
        System.out.println(page);
    }

    @Test
    @Transactional
    @DisplayName("根据mid查询博物馆表test")
    public void getMuseumTest1(){
        Museum museum = museumService.searchMuseum1(1);
        System.out.println(museum);
    }
}
