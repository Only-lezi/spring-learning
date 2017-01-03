package cn.blinkit.transactionManager.xml;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: Only-lezi
 * @Date: 2017-01-02 15:22
 * @Description:
 */

public class OrdersServiceTest {
    @Test
    public void testAccountMoney() {
        ApplicationContext context = new ClassPathXmlApplicationContext("cn/blinkit/transactionManager/xml/spring-txXml.xml");
        OrdersService ordersService = (OrdersService) context.getBean("ordersService");
        ordersService.accountMoney();
    }
}
