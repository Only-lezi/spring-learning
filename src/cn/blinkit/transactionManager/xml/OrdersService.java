package cn.blinkit.transactionManager.xml;

/**
 * @Author: Only-lezi
 * @Date: 2017-01-02 14:49
 * @Description:
 */

public class OrdersService {
    //从spring配置中得到ordersDao对象
    private OrdersDao ordersDao;

    public void setOrdersDao(OrdersDao ordersDao) {
        this.ordersDao = ordersDao;
    }

    //调用dao的方法
    //业务员逻辑层，写转账业务
    public void accountMoney() {
        //小王少1000
        ordersDao.lessMoney();

        int num = 10/0;

        //小马多1000
        ordersDao.moreMoney();
    }
}
