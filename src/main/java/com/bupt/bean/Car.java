package com.bupt.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Car implements ApplicationContextAware {

    ApplicationContext applicationContext;
    public Car() {
        System.out.println("Car constructor has been invoked");
    }
    public void init() {
        System.out.println("Car init method has been invoked");
    }

    public void destory() {
        System.out.println("Car destory method has been invoked");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
