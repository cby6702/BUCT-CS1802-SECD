package com.cs1802.museum.controller;

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

@Controller
public class UploadController {
    @Value("${upload.dev}")
    private String savePath;
    @Value("${myIp.address}")
    private String ip;

    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }

    @PostMapping("/voice/{mid}/{uid}")
    public String upload(@PathVariable("mid") String mid,
                         @PathVariable("uid") String uid,
                         @RequestPart("voice") MultipartFile voice,
                         Model model){
        if (!voice.isEmpty()){
            try {
                voice.transferTo(new File(savePath+
                        voice.getOriginalFilename()));
                String url = ip + "voice/" + voice.getOriginalFilename();
                System.out.println("url = " + url );
                model.addAttribute("url", url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "index";
    }
}
