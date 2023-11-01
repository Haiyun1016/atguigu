package com.atguigu.ioc_03;

import org.springframework.stereotype.Controller;

@Controller
public class UserServiceImpl implements UserService {

    @Override
    public String show() {
        return "UserServiceImpl show();";
    }
}