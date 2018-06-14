package com.bupt;

import com.bupt.tx.TxConfig;
import com.bupt.tx.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestTx {

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TxConfig.class);

        UserService userService = (UserService) applicationContext.getBean("userService");

        userService.insertUser();

    }
}
