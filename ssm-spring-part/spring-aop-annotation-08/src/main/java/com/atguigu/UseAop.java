package com.atguigu;

import com.atguigu.dyn.ProxyFactory;
import com.atguigu.statics.StaticProxyCalculator;

public class UseAop {
    public static void main(String[] args) {

        //房东  目标
        Calculator target = new CalculatorPureImpl();

        //中介  代理
        Calculator proxy = new StaticProxyCalculator(target);

        //调用
        int add = proxy.add(1, 1);
        System.out.println("add = " + add);


        //jdk代理
        ProxyFactory factory = new ProxyFactory(target);
        //要使用接口接值 = 代理对象 【兄弟】
        Calculator proxy1 = (Calculator) factory.getProxy();
        proxy1.add(1,1);
    }

}
