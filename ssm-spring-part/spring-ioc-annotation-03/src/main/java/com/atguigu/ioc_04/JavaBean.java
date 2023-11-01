package com.atguigu.ioc_04;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JavaBean {
    /**
     * <bean id class
     *      <property name="name" value="二狗子"
     */

    //方案一：直接赋值
    private String name = "二狗子";
    //方案二：注解赋值，在成员属性上方加注解@Value，通常不用，多此一举。意义在于读取外部配置时使用，比如有一个数据库配置，如下
    @Value("19")
    private int age;

    //数据库
    //@Value("${jdbc.username}")
    @Value("${jdbc.username:admin}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    //假如要取的值没有值，可以设置一个默认值，语法@Value("${key:value默认值}")


    @Override
    public String toString() {
        return "JavaBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
