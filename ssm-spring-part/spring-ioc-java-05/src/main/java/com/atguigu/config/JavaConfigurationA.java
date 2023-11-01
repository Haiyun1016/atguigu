package com.atguigu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(value = JavaConfigurationB.class)
//如果有多个，加{}，然后 , 隔开
@Configuration
public class JavaConfigurationA {
}
