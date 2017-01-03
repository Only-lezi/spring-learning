package cn.blinkit.annotation.demo1;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Only-lezi on 2016/12/31.
 */
public class UserTest {
    @Test
    public void testUser() {
        //1.加载spring配置文件，根据 配置位置 创建对象
        ApplicationContext context = new ClassPathXmlApplicationContext("/cn/blinkit/annotation/spring-config.xml");
        //2.得到配置创建的对象
        User user = (User) context.getBean("user");
        User user2 = (User) context.getBean("user");
        System.out.println(user);
        System.out.println(user2);
        user.print();
        user2.print();
    }
}
