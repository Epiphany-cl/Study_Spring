import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserFactoryBeanTest {

    ApplicationContext applicationContext;

    @Before
    public void inti(){
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void test(){
        User user = (User)applicationContext.getBean("user");
        System.out.println(user);
    }
}
