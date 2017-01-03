package cn.blinkit.annotation.demo1;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by Only-lezi on 2016/12/31.
 */
/*@Component(value = "user") */  //value就是id值 相当于<bean id="user" class=""/>
@Service(value = "user")    //跟上面的 @Component(value = "user") 功能一样
@Scope(value = "prototype")    //创建的对象是多实例的  单实例是singleton
public class User {
    public void print() {
        System.out.println("User类....print");
    }
}
