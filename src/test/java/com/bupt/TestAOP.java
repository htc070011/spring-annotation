package com.bupt;

import com.bupt.bean.MathCalculator;
import com.bupt.config.MainConfigAOP;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestAOP {

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigAOP.class);

        MathCalculator calculator = applicationContext.getBean(MathCalculator.class);

        calculator.div(1, 0);
        System.out.println(calculator.div(1, 0));

    }
}
