package cn.blinkit.xmlConf.object;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Only-lezi on 2016/12/30.
 */
public class ObjectTest {
    @Test
    public void testUserService() {
        //1.加载spring配置文件，根据创建对象
        ApplicationContext context = new ClassPathXmlApplicationContext("cn/blinkit/xmlConf/spring-config.xml");
        //2.得到配置创建的对象
        UserService userService = (UserService) context.getBean("userService");
        userService.add();
    }
}
