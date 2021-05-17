package com.cs1802.museum.controller;

import com.cs1802.museum.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
public class UploadController {
    @Value("${upload.dev}")
    private String uploadPath;
    @Value("${myIp.address.dev}")
    private String ip;
    @Autowired
    private VoiceService voiceService;

    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }

    @PostMapping("/uploadTest/{mid}/{uid}/{type}/{addition}")
    public String upload(@PathVariable("mid") String midString,
                         @PathVariable("uid") String uidString,
                         @PathVariable("type") String typeString,
                         @PathVariable("addition") String addition,
                         @RequestPart("voice") MultipartFile voice,
                         Model model){
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

                model.addAttribute("url", accessUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "index";
    }
}
