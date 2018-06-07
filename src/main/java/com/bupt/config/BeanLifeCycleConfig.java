package com.bupt.config;

import com.bupt.bean.Car;
import com.bupt.bean.Person;
import org.springframework.context.annotation.*;


//@ComponentScan(value = "com.bupt")
@PropertySource("classpath:person.properties")
@Configuration
public class BeanLifeCycleConfig {

    @Scope(value = "singleton")

    @Bean(initMethod = "init", destroyMethod = "destory")
    public Car car() {
        return new Car();
    }

    @Bean
    public Person person() {

        return new Person();
    }

}
