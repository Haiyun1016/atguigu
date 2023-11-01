package com.atguigu.ioc_01;

import org.springframework.stereotype.Component;

/**
 * projectName: com.atguigu.components
 *
 * description: 普通的组件
 *
 * 1.标记注解
 * 2.配置指定包
 */
@Component  // == <bean id="commonComponent" class="CommonComponent" />
//@Component 默认id是 xxxCommonComponent 若想自定义 @Component(value="ergouzi") == @Component("ergouzi")
public class CommonComponent {
}