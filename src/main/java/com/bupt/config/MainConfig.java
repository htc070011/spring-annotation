package com.bupt.config;


import com.bupt.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;


@Configuration
@ComponentScan(value = "com.bupt", excludeFilters = {
        @ComponentScan.Filter(type=FilterType.ANNOTATION, classes = {Controller.class, Service.class})
})
public class MainConfig {

    @Bean(value = "person")
    public Person person() {
        return new Person("zhangsan", 25);
    }

}
