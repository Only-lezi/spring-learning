package cn.blinkit.annotation.demo2;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.blinkit.annotation.demo1.User;

/**
 * Created by Only-lezi on 2016/12/31.
 */
public class UserServiceTest {
    @Test
    public void testUser() {
        //1.加载spring配置文件，根据 配置位置 创建对象
        ApplicationContext context = new ClassPathXmlApplicationContext("/cn/blinkit/annotation/spring-config.xml");
        //2.得到配置创建的对象
        UserService userService = (UserService) context.getBean("userService1");
        System.out.println("这是注解实现Uservice ... " + userService);
        userService.print();
    }
}
