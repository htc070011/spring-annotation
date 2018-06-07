package com.bupt;

import com.bupt.config.BeanLifeCycleConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanLifeCycleTest {

    AnnotationConfigApplicationContext applicationContext;

    @Before
    public void before() {
        applicationContext = new AnnotationConfigApplicationContext(BeanLifeCycleConfig.class);
    }

    @Test
    public void test() {

        System.out.println("applicationContext has been built");

        String[] names = applicationContext.getBeanDefinitionNames();
        for(String name: names) {
            System.out.println(name);
        }
        applicationContext.close();

    }

    @Test
    public void testPopulate() {
        Object person = applicationContext.getBean("person");

        System.out.println(person);
    }
}
