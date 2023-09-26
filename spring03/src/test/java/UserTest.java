import controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {

  private   ApplicationContext applicationContext ;

    @Before
    public void before(){
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void test(){
        UserController userController = applicationContext.getBean(UserController.class);
        userController.show();
    }
}
