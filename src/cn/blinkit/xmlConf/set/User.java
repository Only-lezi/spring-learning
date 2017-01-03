package cn.blinkit.xmlConf.set;

/**
 * Created by Only-lezi on 2016/12/30.
 */
public class User {
    private String username;

    //set方法注入
    public void setUsername(String username) {
        this.username = username;
    }

    public void print() {
        System.out.println("User :..." + username);
    }
}
