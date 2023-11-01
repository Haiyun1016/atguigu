package com.atguigu.ioc_05;

import org.springframework.beans.factory.FactoryBean;

//制造JavaBean的工厂的bean对象
/**
 * 步骤：
 * 1、实现FactoryBean接口 <返回值泛型>
 */
public class JavaBeanFactoryBean implements FactoryBean<JavaBean> {

    @Override
    public JavaBean getObject() throws Exception {
        //使用你自己的方式实例化对象
        JavaBean javaBean = new JavaBean();
        return javaBean;
    }

    @Override
    public Class<?> getObjectType() {
        return JavaBean.class;
    }
}
