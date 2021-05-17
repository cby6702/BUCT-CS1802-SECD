package com.cs1802.museum.mapper;

import com.cs1802.museum.bean.Voice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface VoiceMapper {
    Voice getVoice0(int id);
    Voice getVoice1(int id);
    Voice getVoice2(int id);

    int add(String voice, int uid, int type, int mid, int id);
}
