package com.atguigu.statics;

import com.atguigu.Calculator;

public class StaticProxyCalculator implements Calculator {

    //和代理类建立关系
    private Calculator calculator;
    //使用构造方法传入目标
    public StaticProxyCalculator(Calculator target) {
        this.calculator = target;

    }

    @Override
    public int add(int i, int j) {
        //非核心业务，中介使用
        System.out.println("i = " + i + ", j = " + j);

        //调用目标
        int result = calculator.add(1, 1);
        System.out.println("result = " + result);
        return 0;
    }

    @Override
    public int sub(int i, int j) {
        return 0;
    }

    @Override
    public int mul(int i, int j) {
        return 0;
    }

    @Override
    public int div(int i, int j) {
        return 0;
    }
}
