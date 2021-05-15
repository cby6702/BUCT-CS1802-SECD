package com.cs1802.museum.service;

import com.cs1802.museum.bean.Museum;
import com.cs1802.museum.bean.Page;

public interface MuseumService {
    Museum searchMuseum(String name);

    Page<Museum> showPage(String city, int pageNo);

    Page<Museum> sortMuseum(String city, int sortItem, int sortKind, int pageNo);
}
