package com.cs1802.museum;

import com.cs1802.museum.bean.Comments;
import com.cs1802.museum.bean.Texts;
import com.cs1802.museum.bean.User;
import com.cs1802.museum.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
//     if(userService.Login(1,"123456"))
//       System.out.println("登录成功");
//     else
//         System.out.println("登录失败");
//     if(userService.Login(1,"kjdsklfjaldsj"))
//        System.out.println("登录成功");
//     else
//        System.out.println("登录失败");
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("注册")
//    public void SignTest(){
//       if(userService.Sign("zxc","123456"))
//           System.out.println("注册成功");
//       else
//           System.out.println("注册失败");
//       if(userService.Sign("ccy","123456"))
//           System.out.println("注册成功");
//       else
//           System.out.println("注册失败");
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("修改个人信息")
//    public void updateUserTest(){
//       User user=new User();
//       user.setBirthday("19980117");
//       user.setGender("男");
//       user.setEmail("1@qq.com");
//       user.setMobile("12345");
//       User newuser=userService.updateUser(35,user);
//       System.out.println(user);
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("我的评论")
//    public void MyComments(){
//        List<Comments> MyCommentsList=userService.MyComments(1);
//        System.out.println(MyCommentsList);
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("删除一条评论")
//    public void deleteComments(){
//       if(userService.deleteComments(4))
//           System.out.println("删除成功");
//       else
//           System.out.println("删除失败");
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("删除我的评论")
//    public void deleteAllComs(){
//       if(userService.deleteAllComs(34))
//           System.out.println("删除成功");
//       else
//           System.out.println("删除失败");
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("我的消息")
//    public void MyTextsTest(){
//        List<Texts> MyTextsList=userService.MyTexts(1);
//        System.out.println(MyTextsList);
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("删除一条消息")
//    public void deleteTextTest(){
//       if(userService.deleteText(1))
//           System.out.println("删除成功");
//       else
//           System.out.println("删除失败");
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("删除我的消息")
//    public void deleteAllTextsTest(){
//       if(userService.deleteAllTexts(34))
//           System.out.println("删除成功");
//       else
//           System.out.println("删除失败");
//    }

}
