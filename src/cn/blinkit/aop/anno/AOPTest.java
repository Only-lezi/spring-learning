package cn.blinkit.aop.anno;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Only-lezi on 2016/12/31.
 */
public class AOPTest {
    @Test
    public void testBook() {
        ApplicationContext context = new ClassPathXmlApplicationContext("cn/blinkit/aop/anno/spring-aopAnno.xml");
        Book book = (Book) context.getBean("book");
        book.add();

    }
}
