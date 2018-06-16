package com.bupt.Ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @EventListener(ApplicationEvent.class)
    public void service(ApplicationEvent event) {
        System.out.println("userService receive " + event);
    }
}
