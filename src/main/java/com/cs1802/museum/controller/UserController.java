package com.cs1802.museum.controller;

import com.cs1802.museum.bean.User;
import com.cs1802.museum.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper fastjson;

    /**
     * 功能：更新uid对应的users表的中的city字段
     *      url: /user/uid
     *      接受参数：data: {
     *          "city" : xxx
     *      }
     *      返回更新后的user对象（String）
     * @param uidString   url上带的（前端传回来的都是string类型）
     * @param json 只有传入city的json数据，需要解析出来
     * @return 成功返回user的json数据，否则是“null”
     */
    @PutMapping("/update/{uid}")
    public String updateCity(@PathVariable("uid") String uidString,
                             @RequestBody String json){
        //1.解析出city
        Map params = null;
        try {
            params = fastjson.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String city = params.get("city").toString();
        //2.将uid类型转为int
        int uid = Integer.parseInt(uidString);
        //3.执行更新的业务
        User user = userService.updateCity(city, uid);
        try {
            return fastjson.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能：返回uid对应的users的所有信息，即user对象
     *      url：/user/uid
     *      返回查找到的user对象(String)
     * @param  uidString    url上带的（前端传回来的都是String类型）
     * @return  查询到返回user对象，否则是“null”
     */

    @GetMapping("/select/{uid}")
    public String getUser(@PathVariable("uid") String uidString){
        int uid=Integer.parseInt(uidString);
        User user = userService.getUser(uid);
        try {
            return fastjson.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能：返回是否成功登录，boolean
     *      url：/user/account/password
     *      返回Boolean true or false
     * @param accountString url上带的（前端传回来的都是String类型）
     * @param passwordString url上带的（前端传回来的都是String类型）
     * @return 用户存在且密码正确返回true，用户不存在或密码不正确均为false
     */

    @GetMapping("/login/{account}/{password}")
    public boolean Login(@PathVariable("account") String accountString,
                         @PathVariable("password") String passwordString){
        return userService.Login(accountString,passwordString);
    }

}
