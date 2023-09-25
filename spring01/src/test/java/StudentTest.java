import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class StudentTest {
    @Test
    public void testStudent() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = applicationContext.getBean("student", Student.class);
        Student student2 = (Student) applicationContext.getBean("student2");
        System.out.println(student);
        System.out.println(student2);

    }
}
