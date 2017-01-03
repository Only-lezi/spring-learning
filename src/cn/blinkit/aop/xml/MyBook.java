package cn.blinkit.aop.xml;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by Only-lezi on 2016/12/31.
 */
public class MyBook {
    public void before1() {
        System.out.println("前置增强......");
    }

    public void after1() {
        System.out.println("后置增强......");
    }

    //环绕通知
    public void around1(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //方法之前
        System.out.println("方法之前.....");

        //执行被增强的方法
        proceedingJoinPoint.proceed();

        //方法之后
        System.out.println("方法之后......");

    }
}
