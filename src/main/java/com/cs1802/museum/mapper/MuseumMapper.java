package com.cs1802.museum.mapper;

import com.cs1802.museum.bean.Museum;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MuseumMapper {
    Museum getMuseum(String name);

    int museumCountByCity(String city);

    List<Museum> museumPageByCity(String city, int begin, Integer pageSize);


    List<Museum> sort(String city, int sortItem, int sortKind, int begin, Integer pageSize);


    Museum getMuseum1(int mid);

    List<Museum> searchByName(String searchItem);

    boolean updateScore(int mid, double exhibitionscore, double environmentscore, double servicescore, double generalscore);
}
