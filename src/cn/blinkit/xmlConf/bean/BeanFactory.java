package cn.blinkit.xmlConf.bean;

/**
 * Created by Only-lezi on 2016/12/30.
 */
public class BeanFactory {
    //静态的方法，返回bean的对象
    public static Bean getBean() {
        return new Bean();
    }
}
