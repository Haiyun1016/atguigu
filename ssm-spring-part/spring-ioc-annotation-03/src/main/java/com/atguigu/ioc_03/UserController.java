package com.atguigu.ioc_03;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//user的表述层
@Controller
public class UserController {
    //相当于property userService 对应类型的bean装配
    //自动装配注解DI ： 1.在IoC容器中查找符合类型的组件对象  2.设置给当前属性（DI）
    @Autowired
    private UserService userService;
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    public void show() {
        //调用业务层的show方法
        String show = userService.show();
        System.out.println("show = " + show);
    }
}
