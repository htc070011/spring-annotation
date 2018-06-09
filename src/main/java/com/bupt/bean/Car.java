package com.bupt.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

public class Car implements ApplicationContextAware, EmbeddedValueResolverAware, BeanNameAware {

    ApplicationContext applicationContext;

    String beanName;

    String resolveResult;

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

    @Override
    public void setBeanName(String s) {
        this.beanName = s;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.resolveResult = stringValueResolver.resolveStringValue("try to resolve #{26-1} and ${name.nickname}");
    }

    @Override
    public String toString() {
        return "Car{" +
                "beanName='" + beanName + '\'' +
                ", resolveResult='" + resolveResult + '\'' +
                '}';
    }
}
