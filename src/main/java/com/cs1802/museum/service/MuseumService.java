package com.cs1802.museum.service;

import com.cs1802.museum.bean.Comments;
import com.cs1802.museum.bean.Museum;
import com.cs1802.museum.bean.Page;
import com.cs1802.museum.bean.Score;

import java.util.List;

public interface MuseumService<T> {
    Museum searchMuseum(String name);

    Page<Museum> showPage(String city, int pageNo);

    Page<Museum> sortMuseum(String city, int sortItem, int sortKind, int pageNo);

    Museum searchMuseum1(int mid);

    List<T> search(String searchKind, String searchItem);

    boolean updateScore(Comments comment);
    Score getScore(int mid);
}
