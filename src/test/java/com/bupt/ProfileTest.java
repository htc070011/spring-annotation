package com.bupt;

import com.bupt.bean.Phone;
import com.bupt.config.MainConfiOfProfile;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProfileTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfiOfProfile.class);
//        Phone phone = applicationContext.getBean(Phone.class);
        String[] names = applicationContext.getBeanNamesForType(Phone.class);
        for(String name: names) {
            Phone phone = (Phone) applicationContext.getBean(name);
            System.out.println(phone);
        }

    }
}
