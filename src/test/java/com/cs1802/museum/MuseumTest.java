package com.cs1802.museum;

import com.cs1802.museum.bean.Museum;
import com.cs1802.museum.bean.User;
import com.cs1802.museum.service.MuseumService;
import com.cs1802.museum.service.UserService;
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
    @DisplayName("根据mid查询博物馆表test")
    public void getMuseumTest(){
        Museum museum = museumService.searchMuseum(1);
        System.out.println(museum);
    }
}
