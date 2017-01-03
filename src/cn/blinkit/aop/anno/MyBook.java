package cn.blinkit.aop.anno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by Only-lezi on 2016/12/31.
 */
@Aspect
public class MyBook {
    //在方法上面使用注解完成增强配置
    @Before(value = "execution(* cn.blinkit.aop.anno.Book.*(..))")
    public void before1() {
        System.out.println("前置增强...注解版本...");
    }

    @After(value = "execution(* cn.blinkit.aop.anno.Book.*(..))")
    public void after1() {
        System.out.println("后置增强...注解版本...");
    }

    //环绕通知
    @Around(value = "execution(* cn.blinkit.aop.anno.Book.*(..))")
    public void around1(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //方法之前
        System.out.println("方法之前...注解版本...");

        //执行被增强的方法
        proceedingJoinPoint.proceed();

        //方法之后
        System.out.println("方法之后...注解版本...");

    }
}
