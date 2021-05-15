package com.cs1802.museum.controller;

import com.cs1802.museum.bean.Voice;
import com.cs1802.museum.service.VoiceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voice")
public class VoiceController {
    @Autowired
    private VoiceService voiceService;
    @Autowired
    private ObjectMapper fastjson;

    /**
     * 功能：根据类型type和id查询音频表并返回对应讲解音频
     *      url: /voice/search/{type}/{id}
     *      返回voice对象（String）
     * @param  typeString,idString   url上带的（前端传回来的都是string类型）
     * @return  voice或null
     */
    @GetMapping("/search/{type}/{id}")
    public String getVoice(@PathVariable("type") String typeString,
                           @PathVariable("id") String idString){
        //1.将传入的type和id从String转为int
        int type = Integer.parseInt(typeString);
        int id = Integer.parseInt(idString);
        //2.执行查询业务逻辑
        Voice voice = voiceService.searchVoice(type,id);
        try{
            return fastjson.writeValueAsString(voice);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
