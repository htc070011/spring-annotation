package com.bupt;

import com.bupt.Ext.ExtConfig;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestExt {

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);

        applicationContext.publishEvent(new ApplicationEvent(new String("defined event")) {
        });

        applicationContext.close();
    }
}
