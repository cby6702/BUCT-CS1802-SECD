package com.cs1802.museum.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMVCConfig implements WebMvcConfigurer {
    @Value("${upload.dev}")
    private String savePath;

    /**
     * 开启服务器内音频的地址映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/voice/**")
                .addResourceLocations("file:" + savePath);
    }
}
