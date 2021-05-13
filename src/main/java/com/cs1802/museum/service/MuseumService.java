package com.cs1802.museum.service;

import com.cs1802.museum.bean.Museum;
import com.cs1802.museum.bean.Page;

import java.util.List;

public interface MuseumService {
    Museum searchMuseum(int mid);

    Page<Museum> showPage(String city, int pageNo);
}
