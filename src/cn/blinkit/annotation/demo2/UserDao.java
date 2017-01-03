package cn.blinkit.annotation.demo2;

import org.springframework.stereotype.Component;

/**
 * Created by Only-lezi on 2016/12/31.
 */
@Component(value = "userDao")
public class UserDao {
    public void print() {
        System.out.println("UserDao.....print");
    }
}
