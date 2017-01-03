package cn.blinkit.annotation.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Only-lezi on 2016/12/31.
 */
@Service(value = "userService1")
public class UserService {
    //得到dao对象
    //1.定义dao类型属性，使用注解方式不需要set方法
    //在dao属性上面使用注解完成对象注入
    /*@Autowired*/         //自动装配  根据注入的类对象去找，跟dao里面类配置的value名字无关
    @Resource(name = "userDao")    //name 属性写 注解创建dao对象value值
    private UserDao userDao;

    public void print() {
        System.out.println("UserService......print..");
        userDao.print();
    }
}
