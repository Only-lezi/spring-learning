package cn.blinkit.annotation.xmlAndAnno;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Only-lezi on 2016/12/31.
 */
public class BookServiceTest {
    @Test
    public void testBookService() {
        ApplicationContext context = new ClassPathXmlApplicationContext("cn/blinkit/annotation/xmlAndAnno/xmlAndAnno.xml");
        BookService bookService = (BookService) context.getBean("bookService");
        System.out.println(bookService);
        bookService.print();
    }
}
