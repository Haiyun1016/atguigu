package com.atguigu.ioc_04;

public class JavaBean {

    //方法必须是public，void ，必须无参
    //命名随意
    //初始化方法 -> 写初始化业务逻辑即可
    public void init() {
        System.out.println("JavaBean.init");
    }

    //销毁方法，要求同样
    public void clear() {
        System.out.println("JavaBean.clear");
    }
}
