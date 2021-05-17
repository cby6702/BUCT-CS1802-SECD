package com.cs1802.museum.controller;

import com.cs1802.museum.bean.Voice;
import com.cs1802.museum.service.VoiceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/voice")
public class VoiceController {
    @Autowired
    private VoiceService voiceService;
    @Autowired
    private ObjectMapper fastjson;
    @Value("${upload.dev}")
    private String uploadPath;
    @Value("myIp.address.dev")
    private String ip;

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

    /**
     * 上传音频，插入voice表 当讲解是博物馆讲解时addition为0，其余时候为对应的cid/eid
     * @param midString
     * @param uidString
     * @param typeString
     * @param addition
     * @param voice
     * @return
     */
    @PostMapping("/upload/{mid}/{uid}/{type}/{addition}")
    public String upload(@PathVariable("mid") String midString,
                         @PathVariable("uid") String uidString,
                         @PathVariable("type") String typeString,
                         @PathVariable("addition") String addition,
                         @RequestPart("voice") MultipartFile voice){
        int res = 0;
        //处理前端数据的类型
        int mid = Integer.parseInt(midString);
        int uid = Integer.parseInt(uidString);
        int type = Integer.parseInt(typeString);
        int id = Integer.parseInt(addition);

        if (!voice.isEmpty()){//文件不为空
            try {
                //为了避免文件名重复，加上时间戳
                long time = new Date().getTime();
                String fileName = time + voice.getOriginalFilename();
                voice.transferTo(new File(uploadPath + fileName));
                String accessUrl = ip + "voice/" + fileName;
                System.out.println("url = " + accessUrl );

                //插入到voice表中
                res = voiceService.add(accessUrl, uid, type, mid, id);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res==1 ? "true" : "false";
    }
}
