package com.cs1802.museum.service;

import com.cs1802.museum.bean.Voice;
public interface VoiceService {
    Voice searchVoice(int type,int id);

    int add(String voice, int uid, int type, int mid, int id);
}
