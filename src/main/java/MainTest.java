import com.bupt.bean.Person;
import com.bupt.config.MainConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
//        Person person = applicationContext.getBean(Person.class);
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);
    }
}
