package com.cs1802.museum.controller;

import com.cs1802.museum.bean.Comments;
import com.cs1802.museum.bean.Texts;
import com.cs1802.museum.bean.User;
import com.cs1802.museum.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
     *      url: /user/update/{uid}
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
     *      url：/user/select/{uid}
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
     *      url：/user/login/{uid}/{password}
     *      返回Boolean true or false
     * @param uidString url上带的（前端传回来的都是String类型）
     * @param passwordString url上带的（前端传回来的都是String类型）
     * @return 用户存在且密码正确返回true，用户不存在或密码不正确均为false
     */

    @GetMapping("/login/{uid}/{password}")
    public boolean Login(@PathVariable("uid") String uidString,
                         @PathVariable("password") String passwordString){
        int uid=Integer.parseInt(uidString);
        return userService.Login(uid,passwordString);
    }

    /**
     * 功能：返回是否注册登录，boolean
     *      url：/user/sign
     *      接受参数：data：{
     *          “name”：XXX
     *          “password”：XXX
     *      }
     *      返回Boolean true or false
     * @param json 传入的name和password的数据，需要解析出来
     * @return 用户名不存在且注册成功，返回true；用户存在或注册失败均为false
     */

    @PostMapping("/sign")
    public boolean Sign(@RequestBody String json){
        //解析出name和password
        Map params = null;
        try {
            params = fastjson.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String nameString=params.get("name").toString();
        String passwordString=params.get("password").toString();
        String emailString=params.get("email").toString();
        String mobileString=params.get("mobile").toString();

        return userService.Sign(nameString,passwordString,emailString,mobileString);
    }

    /**
     * 功能：更新uid对应的users表中的name,gender,birthday,email,mobile,avatarUrl字段
     *      url: /user/updateUser/{uid}
     *      接受参数：data: {
     *          "name" : xxx
     *          "gender" : xxx
     *          "birthday" : xxx
     *          "email" : xxx
     *          "mobile" : xxx
     *          "avatarUrl" : xxx
     *      }
     *      返回更新后的user对象（String）
     * @param uidString   url上带的（前端传回来的都是string类型）
     * @param json 有传入name,gender,birthday,email,mobile,avatarUrl的json数据，需要解析出来
     * @return 成功返回user的json数据，否则是“null”
     */

    @PutMapping("/updateUser/{uid}")
    public String updateUser(@PathVariable ("uid") String uidString,
                             @RequestBody String json){
        User _user=null;
        //解析出一个user对象
        try {
            _user = fastjson.readValue(json, new TypeReference<User>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //将uid转换成int
        int uid=Integer.parseInt(uidString);
        //执行更新的业务
        User user= userService.updateUser(uid,_user);
        try {
            return fastjson.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能：返回uid对应的所有评论，即List<Comments>
     *      url：/user/MyComments/{uid}
     *      返回查找到的所有评论，即List<Comments>(String)
     * @param  uidString    url上带的（前端传回来的都是String类型）
     * @return  查询到返回List<Comments>，否则是“null”
     */

    @GetMapping("/MyComments/{uid}")
    public String MyComments(@PathVariable("uid") String uidString){
        int uid=Integer.parseInt(uidString);

        List<Comments> MyCommentsList= userService.MyComments(uid);
        try {
            return  fastjson.writeValueAsString(MyCommentsList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能：返回是否成功删除一条评论，boolean
     *      url：/user/deletComments/{aid}
     *      返回Boolean true or false
     * @param aidString  url上带的（前端传回来的都是String类型）
     * @return 删除成功，返回true；删除失败，返回false
     */

    @DeleteMapping("/deleteComments/{aid}")
    public boolean deleteComments(@PathVariable("aid") String aidString){
        int aid=Integer.parseInt(aidString);

        return userService.deleteComments(aid);
    }

    /**
     * 功能：返回是否成功删除我的全部评论，boolean
     *      url：/user/deletAllComs/{uid}
     *      返回Boolean true or false
     * @param uidString  url上带的（前端传回来的都是String类型）
     * @return 删除成功，返回true；删除失败，返回false
     */

    @DeleteMapping("/deleteAllComs/{uid}")
    public boolean deleteAllComs(@PathVariable("uid") String uidString){
        int uid=Integer.parseInt(uidString);

        return userService.deleteAllComs(uid);
    }

    /**
     * 功能：返回uid对应的所有消息，即List<Texts>
     *      url：/user/MyTexts/{uid}
     *      返回查找到的所有评论，即List<Texts>(String)
     * @param  uidString    url上带的（前端传回来的都是String类型）
     * @return  查询到返回List<Texts>，否则是“null”
     */

    @GetMapping("/MyTexts/{uid}")
    public String MyTexts(@PathVariable("uid") String uidString){
        int uid=Integer.parseInt(uidString);

        List<Texts> MyTextsList= userService.MyTexts(uid);
        try {
            return  fastjson.writeValueAsString(MyTextsList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能：返回是否成功删除一条消息，boolean
     *      url：/user/deleteText/{tid}
     *      返回Boolean true or false
     * @param tidString  url上带的（前端传回来的都是String类型）
     * @return 删除成功，返回true；删除失败，返回false
     */

    @DeleteMapping("/deleteText/{tid}")
    public boolean deleteText(@PathVariable("tid") String tidString){
        int tid=Integer.parseInt(tidString);

        return userService.deleteComments(tid);
    }

    /**
     * 功能：返回是否成功删除我的全部消息，boolean
     *      url：/user/deletAllTexts/{uid}
     *      返回Boolean true or false
     * @param uidString  url上带的（前端传回来的都是String类型）
     * @return 删除成功，返回true；删除失败，返回false
     */

    @DeleteMapping("/deleteAllTexts/{uid}")
    public boolean deleteAllTexts(@PathVariable("uid") String uidString){
        int uid=Integer.parseInt(uidString);

        return userService.deleteAllTexts(uid);
    }


}
