package cn.blinkit.xmlConf.ioc;

/**
 * Created by Only-lezi on 2016/12/30.
 */
public class User {
    //spring默认创建对象的时候会调用无参的构造方法
    public User() {
    }

    public void add() {
        System.out.println("Hello World...");
    }
}
