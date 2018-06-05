package com.bupt.config;


import com.bupt.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


/*
*   在单实例singleton情况下，在ioc容器创建过程中创建对象至容器
*   在prototype情况下，在ioc容器创建完成后，getBean的时候创建对象
*   prototype创建多个对象
*
*
*
* */
@Configuration
public class MainConfig2 {

    @Scope("prototype")
    @Bean
    public Person person() {
        System.out.println("向容器中添加person");
        return new Person("lisi", 22);
    }

}
