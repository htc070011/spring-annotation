package com.bupt;

import com.bupt.bean.Boss;
import com.bupt.bean.Car;
import com.bupt.config.AutowireConfig;
import com.bupt.dao.BookDao;
import com.bupt.service.BookService;
import org.junit.Test;
import org.springframework.beans.factory.Aware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutowireTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutowireConfig.class);

        BookService bookService = (BookService) applicationContext.getBean("bookService");
//
//        System.out.println("booDao in bookSerivre = " + bookService.bookDao);
//
//        System.out.println("booDao in context = " + applicationContext.getBean(BookDao.class));

//        System.out.println(bookService);
//        Boss boss = applicationContext.getBean(Boss.class);
//
//        System.out.println(boss);

        Car car = applicationContext.getBean(Car.class);
        System.out.println(car);
    }

}
