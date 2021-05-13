package com.cs1802.museum.service;

import com.cs1802.museum.bean._Collection;

import java.util.List;

public interface CollectionService {
    List<_Collection> searchCollection(int mid);
}
