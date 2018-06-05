package com.bupt.config;


import com.bupt.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MainConfig {

    @Bean(value = "person")
    public Person person() {
        return new Person("zhangsan", 25);
    }

}
