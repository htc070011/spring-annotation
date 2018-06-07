package com.bupt.bean;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Weather {

    public Weather() {
        System.out.println("Weather constructor has been invoked");
    }

    @PostConstruct
    public void init() {
        System.out.println("Weather PostConstruct has been invoked");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Weather destroy method has been invoked");
    }
}
