package com.bupt;

import com.bupt.bean.Person;
import com.bupt.config.MainConfig;
import com.bupt.config.MainConfig2;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

public class IOCTest {

    ApplicationContext applicationContext;

    @Before
    public void before() {
        applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
    }

    @Test
    public void test01() {
        String[] names = applicationContext.getBeanDefinitionNames();
        for(String name: names) {
            System.out.println(name);
        }

    }

    @Test
    public void test02() {

        System.out.println("ioc容器创建完成");
        Person person = applicationContext.getBean(Person.class);
        Person person2 = applicationContext.getBean(Person.class);
//        System.out.println(person);
        System.out.println(person == person2);

    }

    @Test
    public void test03() {
        Environment environment = applicationContext.getEnvironment();
        String os = environment.getProperty("os.name");
        System.out.println(os);
        Map<String, Person> map = applicationContext.getBeansOfType(Person.class);
        System.out.println(map);


    }
    @Test
    public void test04() {
        printBean(applicationContext);

        //工厂创建的是通过getObject方法返回的对象
        Object bean1 = applicationContext.getBean("&colorFactory");
        System.out.println(bean1.getClass());
        Object bean2 = applicationContext.getBean("colorFactory");
        System.out.println(bean1 == bean2);

//        System.out.println("class = " + colorFactory.getClass());

    }

    private void printBean(ApplicationContext applicationContext) {
        String[] names = applicationContext.getBeanDefinitionNames();
        for(String name: names) {
            System.out.println(name);
        }
    }

}
