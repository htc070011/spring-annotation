package com.bupt.config;


import com.bupt.bean.Person;
import com.bupt.service.BookService;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;



@Configuration
@ComponentScans({
        @ComponentScan(value = "com.bupt", includeFilters = {
//                @ComponentScan.Filter(type=FilterType.ANNOTATION, classes = {Controller.class}),
//                @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, classes = {BookService.class}),
                @ComponentScan.Filter(type=FilterType.CUSTOM, classes = {MyTypeFilter.class})
        }, useDefaultFilters = false)
})
public class MainConfig {

    @Bean(value = "person")
    public Person person() {
        return new Person("zhangsan", 25);
    }

}
