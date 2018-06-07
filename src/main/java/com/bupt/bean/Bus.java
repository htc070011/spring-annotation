package com.bupt.bean;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Bus implements InitializingBean, DisposableBean {


    public Bus() {
        System.out.println("Bus constructor has been invoked");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Bus destory method has been invoked");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Bus init method has been invoked");
    }
}
