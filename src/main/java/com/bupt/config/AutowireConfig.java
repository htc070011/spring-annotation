package com.bupt.config;

import com.bupt.bean.Boss;
import com.bupt.bean.Car;
import com.bupt.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

@PropertySource("classpath:/person.properties")
@ComponentScan({"com.bupt.controller", "com.bupt.dao", "com.bupt.service"})
@Configuration
public class AutowireConfig {

    @Primary
    @Bean
    public BookDao bookDao1() {
        return new BookDao(1);
    }

    @Bean
    public Boss boss(@Autowired Car car) {
        return new Boss(car);
    }

    @Bean(value = "xiaopaoche")
    public Car car() {
        return new Car();
    }

}
