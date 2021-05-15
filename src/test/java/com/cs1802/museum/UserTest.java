package com.cs1802.museum;

import com.cs1802.museum.bean.User;
import com.cs1802.museum.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@DisplayName("用户测试")
public class UserTest {
//    @Autowired
//    private UserService userService;
//
//   @Test
//    @Transactional
//    @DisplayName("更新城市字段的测试")
//    public void updateCityTest(){
//        User user = userService.updateCity("北京", 1);
//        System.out.println(user);
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("查找用户信息")
//    public void getUserTest(){
//        User user=userService.getUser(1);
//        System.out.println(user);
//    }
//
//   @Test
//   @Transactional
//   @DisplayName("登录")
//   public void LoginTest(){
//     if(userService.Login("zxc-4947897","123456"))
//       System.out.println("登录成功");
//     else
//         System.out.println("登录失败");
//     if(userService.Login("zxc-4947897","kjdsklfjaldsj"))
//        System.out.println("登录成功");
//     else
//        System.out.println("登录失败");
//    }
//    @Test
//    @Transactional
//    @DisplayName("登录")
//    public void LoginTest(){
//        if(userService.Login("zxc-4947897","123456"))
//            System.out.println("登录成功");
//        else
//            System.out.println("登录失败");
//        if(userService.Login("zxc-4947897","kjdsklfjaldsj"))
//            System.out.println("登录成功");
//        else
//            System.out.println("登录失败");
//
//    }
}
