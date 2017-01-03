package cn.blinkit.aop.xml;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Only-lezi on 2016/12/31.
 */
public class AOPTest {
    @Test
    public void testBook() {
        ApplicationContext context = new ClassPathXmlApplicationContext("cn/blinkit/aop/xml/spring-aopXml.xml");
        Book book = (Book) context.getBean("book");
        book.add();

    }
}
