package cn.blinkit.c3p0;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: Only-lezi
 * @Date: 2017-01-01 23:06
 * @Description:
 */

public class UserServiceTest {
    @Test
    public void testAdd() {
        ApplicationContext context = new ClassPathXmlApplicationContext("cn/blinkit/c3p0/spring-c3p0.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.add();
    }
}
