package com.bupt.Ext;

import com.bupt.bean.Blue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.bupt.Ext")
public class ExtConfig {
    @Bean
    public Blue blue() {
        return new Blue();
    }
}
