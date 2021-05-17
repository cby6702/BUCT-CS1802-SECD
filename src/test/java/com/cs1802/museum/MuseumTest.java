package com.cs1802.museum;

import com.cs1802.museum.bean.Museum;
import com.cs1802.museum.bean.Page;
import com.cs1802.museum.bean.Score;
import com.cs1802.museum.service.MuseumService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.util.List;

@SpringBootTest
@DisplayName("MuseumTest")
@Transactional
public class MuseumTest {
    @Autowired
    private MuseumService museumService;
//
//    @Test
//    @DisplayName("根据name查询博物馆表test")
//    public void getMuseumTest(){
//        Museum museum = museumService.searchMuseum("北京博物馆");
//        System.out.println(museum);
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("博物馆排名")
//    public void sortMuseumTest(){
//        Page<Museum> page = museumService.sortMuseum("all", 0, 1, 1);
//        System.out.println(page);
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("根据mid查询博物馆表test")
//    public void getMuseumTest1(){
//        Museum museum = museumService.searchMuseum1(1);
//        System.out.println(museum);
//    }
//    @Test
//    @DisplayName("博物馆排名")
//    public void sortMuseumTest(){
//        Page<Museum> page = museumService.sortMuseum("京", 0, 1, 1);
//        System.out.println(page);
//    }
//    @Test
//    @DisplayName("查询页查询功能")
//    public void searchTest(){
//        List list = museumService.search("博物馆", "北京");
//        System.out.println(list);
//        System.out.println(list.size());
//    }
//
//    @Test
//    @DisplayName("根据mid查询博物馆评分test")
//    public void showScoreTest() {
//        Score score = museumService.getScore(3);
//        System.out.println(score);
//    }
}
