package cn.blinkit.transactionManager.anno;

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
        ApplicationContext context = new ClassPathXmlApplicationContext("cn/blinkit/transactionManager/anno/spring-txAnno.xml");
        OrdersService ordersService = (OrdersService) context.getBean("ordersService1");
        ordersService.accountMoney();
    }
}
