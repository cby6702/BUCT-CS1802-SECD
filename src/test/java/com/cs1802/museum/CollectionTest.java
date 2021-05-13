package com.cs1802.museum;

import com.cs1802.museum.bean._Collection;
import com.cs1802.museum.service.CollectionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@DisplayName("CollectionTest")
public class CollectionTest {
    @Autowired
    private CollectionService collectionService;

    @Test
    @Transactional
    @DisplayName("根据mid查询藏品表test")
    public void getCollectionTest() {
        List<_Collection> collectionlist = collectionService.searchCollection(1);
        System.out.println(collectionlist);
    }
}
