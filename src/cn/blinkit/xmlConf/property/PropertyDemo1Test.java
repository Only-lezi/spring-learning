package cn.blinkit.xmlConf.property;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Only-lezi on 2016/12/30.
 */
public class PropertyDemo1Test {
    @Test
    public void testPropertyDemo1() {
        //1.加载spring配置文件，根据创建对象
        ApplicationContext context = new ClassPathXmlApplicationContext("cn/blinkit/xmlConf/spring-config.xml");
        //2.得到配置创建的对象
        PropertyDemo1 propertyDemo1 = (PropertyDemo1) context.getBean("propertyDemo1");
        propertyDemo1.add();
    }
}
