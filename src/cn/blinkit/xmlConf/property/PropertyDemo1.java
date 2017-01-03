package cn.blinkit.xmlConf.property;

/**
 * Created by Only-lezi on 2016/12/30.
 */
public class PropertyDemo1 {
    private String username;

    public PropertyDemo1(String username) {
        this.username = username;
    }

    public void add() {
        System.out.println("PropertyDemo1..:" + username);
    }
}
