package com.bupt.config;

import com.bupt.bean.LogAspect;
import com.bupt.bean.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@Configuration
public class MainConfigAOP {
    
    @Bean
    public MathCalculator calculator() {
        return new MathCalculator();

    }

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }
}
