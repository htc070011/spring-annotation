package com.bupt.config;

import com.bupt.bean.Phone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.EmbeddedValueResolver;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

@PropertySource("classpath:/phone.properties")
@Configuration
public class MainConfiOfProfile implements EmbeddedValueResolverAware{
    @Value("weChat")
    String app;

    StringValueResolver stringValueResolver;

    @Bean
    public Phone phoneTest(@Value("test") String scen) {
        Phone phone = new Phone();
        phone.setApp(app);
        phone.setScen(scen);
        phone.setName(stringValueResolver.resolveStringValue("${test.name}"));
        return phone;
    }
    @Bean
    public Phone phoneProd(@Value("prod") String scen) {
        Phone phone = new Phone();
        phone.setApp(app);
        phone.setScen(scen);
        phone.setName(stringValueResolver.resolveStringValue("${prod.name}"));
        return phone;
    }
    @Bean
    public Phone phoneDev(@Value("dev") String scen) {
        Phone phone = new Phone();
        phone.setApp(app);
        phone.setScen(scen);
        phone.setName(stringValueResolver.resolveStringValue("${dev.name}"));
        return phone;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.stringValueResolver = stringValueResolver;
    }
}
