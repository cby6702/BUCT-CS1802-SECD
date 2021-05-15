package com.cs1802.museum.service.impl;

import com.cs1802.museum.bean.Voice;
import com.cs1802.museum.mapper.VoiceMapper;
import com.cs1802.museum.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VoiceServiceImpl implements VoiceService {
    @Autowired
    private VoiceMapper voiceMapper;
    /*
    业务：根据类型type和id查询音频表并返回对应讲解音频
    逻辑：1.判断传入type，若type=0（博物馆讲解）则根据mid返回voice对象
                       若type=1（展览讲解）则根据eid返回voice对象
                       若type=2（藏品讲解）则根据cid返回voice对象
     */
    @Override
    public Voice searchVoice(int type,int id) {
        //1.判断type值，若type=0，则查询voice表中mid对应voice对象
        if(type==0)
            return voiceMapper.getVoice0(id);
        //2.判断type值，若type=1，则查询voice表中eid对应voice对象
        else if(type==1)
            return voiceMapper.getVoice1(id);
        //3.判断type值，若type=2，则查询voice表中cid对应voice对象
        else
            return voiceMapper.getVoice2(id);
    }

}
