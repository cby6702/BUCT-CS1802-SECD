# 后端开发手册

## 准备工作
1. 每次修改代码前先pull最新的版本
2. 将公网的数据库复制到自己的本地库中，每次记得先把application.yml文件中关于数据库的账号密码给修改成自己的

## 接口规范
### 使用RESTfull风格写接口url
| 请求方式 | 对应数据库的操作 | 示例 |
| ---- | ---- | ---- |
| GET | 查询select | |
| POST | 插入insert | | 
| PUT | 修改update | |
| DELETE | 删除delet | |

详情请见[restfull](https://blog.csdn.net/qq_41606973/article/details/86352787?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control)

## 编写规范
### 例子
按照图片顺序快速掌握开发流程和需要写的注释规范

![顺序](https://www.hualigs.cn/image/608c153f2bad0.jpg)
