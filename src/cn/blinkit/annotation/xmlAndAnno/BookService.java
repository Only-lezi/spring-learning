package cn.blinkit.annotation.xmlAndAnno;

import javax.annotation.Resource;

/**
 * Created by Only-lezi on 2016/12/31.
 */
public class BookService {
    //得到bookDao和ordersDao
    @Resource(name = "bookDao")
    private BookDao bookDao;

    @Resource(name = "ordersDao")
    private OrdersDao orderDao;

    public void print() {
        System.out.println("BookService......");
        bookDao.book();
        orderDao.buy();
    }
}
