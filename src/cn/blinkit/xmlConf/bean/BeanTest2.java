package cn.blinkit.xmlConf.bean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Only-lezi on 2016/12/30.
 */
public class BeanTest2 {
    @Test
    public void beanTest2() {
        //1.加载spring配置文件，根据创建对象
        ApplicationContext context = new ClassPathXmlApplicationContext("cn/blinkit/xmlConf/spring-config.xml");
        //2.得到配置创建的对象
        Bean bean = (Bean) context.getBean("bean2");
        System.out.println(bean);
        bean.add();
    }
}
