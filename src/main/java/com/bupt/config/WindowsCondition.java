package com.bupt.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class WindowsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {


        //获取ioc使用的beanfactory
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
        //获取bean定义的注册类
        BeanDefinitionRegistry beanDefinitionRegistry = conditionContext.getRegistry();
        //获取类加载器
        ClassLoader classLoader = conditionContext.getClassLoader();
        //获取当前环境
        Environment environment = conditionContext.getEnvironment();


        String[] beanNames = beanDefinitionRegistry.getBeanDefinitionNames();

        String os = environment.getProperty("os.name");

        if(os.contains("indow")) {
            return true;
        }

        return false;
    }
}
