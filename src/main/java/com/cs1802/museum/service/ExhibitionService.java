package com.cs1802.museum.service;

import com.cs1802.museum.bean.Exhibition;

import java.util.List;

public interface ExhibitionService {
    List<Exhibition> searchExhibition(int mid);
}
