package com.bupt.config;


import com.bupt.bean.Blue;
import com.bupt.bean.ColorFactory;
import com.bupt.bean.Person;
import com.bupt.bean.Red;
import org.springframework.context.annotation.*;


/*
*   在单实例singleton情况下，在ioc容器创建过程中创建对象至容器
*   在prototype情况下，在ioc容器创建完成后，getBean的时候创建对象
*   prototype创建多个对象
*
*
*
* */

@Import({Blue.class, Red.class, MyImportSelector.class, MyBeanDefinitionRegistrar.class})
@Configuration
public class MainConfig2 {

//    @Scope("prototype")
    @Lazy
    @Bean
    public Person person() {
        System.out.println("向容器中添加person");
        return new Person("lisi", 22);
    }

    @Conditional(WindowsCondition.class)
    @Bean(value = "rose")
    public Person person01() {

        return new Person("rose", 50);

    }

    @Conditional(LinuxCondition.class)
    @Bean(value = "jack")
    public Person person02() {

        return new Person("jack", 49);
    }


    @Bean
    public ColorFactory colorFactory() {
        return new ColorFactory();

    }



}
