# Spring学习笔记 (四)  

>**目录：**
> - [AOP](#AOP) 
> - [Spring的AOP操作](#Spring的AOP操作)
> - [使用表达式配置切入点](#使用表达式配置切入点)
> - [使用xml实现aop](#使用xml实现aop)
> - [使用注解实现aop](#使用注解实现aop)
> - [log4j介绍](#log4j介绍)

---
### AOP 

1.aop概念

- aop：面向切面（方面）编程，扩展功能不修改源代码实现  
- AOP采取**横向抽取机制**，取代了传统纵向继承体系重复性代码（性能监视、事务管理、安全检查、缓存）

2.aop底层原理  
3.aop操作相关术语  

---
### Spring的AOP操作 

1. 在spring里面进行aop操作，使用aspectj实现  
（1）aspectj不是spring的一部分，和spring一起使用进行aop操作  
（2）Spring2.0以后新增了对AspectJ的支持  

2. 使用aspectj实现aop有两种方式  
（1）基于aspectj的xml配置  
（2）基于aspectj的注解方式  

**AOP入门：**  
导入4个jar包：  
`aopalliance-1.0.jar`  
`aspectjweaver.jar`  
`spring-aop-4.3.1.RELEASE.jar`  
`spring-aspects-4.3.1.RELEASE.jar`  

**引入约束文件**
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:aop="http://www.springframework.org/schema/aop" xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd"> 
        ...

</beans>
```
---
###使用表达式配置切入点

1. 切入点：实际增强的方法

2. 常用的表达式  
execution(<访问修饰符>? <返回类型> <方法名>(<参数>)<异常>)  
（1）对包内的add方法进行增强  
`execution(* cn.blinkit.aop.Book.add(..))`  
（2）* 是对类里面的所有方法进行增强  
`execution(* cn.blinkit.aop.Book.*(..))`  
（3）\*.\* 是所有的类中的方法进行增强  
`execution(* *.*(..))`  
（4）匹配所有save开头的方法  
`execution(* save*(..))`  

---
###使用xml实现aop

**aop配置代码：**
Book
```
public class Book {
    public void add() {
        System.out.println("add......");
    }
}
```

MyBook
```
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
```

xml配置
```
    <!--1. 配置对象-->
    <bean id="book" class="cn.blinkit.aop.Book"></bean>
    <bean id="myBook" class="cn.blinkit.aop.MyBook"></bean>

    <!--2. 配置aop操作-->
    <aop:config>
        <!--2.1 配置切入点-->
        <aop:pointcut id="pointcut1" expression="execution(* cn.blinkit.aop.Book.*(..))"></aop:pointcut>

        <!--2.2 配置切面
                把增强用到方法上面
        -->
        <aop:aspect ref="myBook">
            <!--
                aop:before   :前置通知
                aop:after    :后置通知
                aop:around   :环绕通知
                配置增强类型
                method : 增强类里面使用哪个方法作为前置
            -->
            <aop:before method="before1" pointcut-ref="pointcut1"></aop:before>
            <aop:after method="after1" pointcut-ref="pointcut1"></aop:after>
            <aop:around method="around1" pointcut-ref="pointcut1"></aop:around>
        </aop:aspect>
    </aop:config>
```

测试代码
```
public class AOPTest {
    @Test
    public void testBook() {
        ApplicationContext context = new ClassPathXmlApplicationContext("cn/blinkit/aop/spring-aop.xml");
        Book book = (Book) context.getBean("book");
        book.add();

    }
}
```
---
###使用注解实现aop   

1. 创建对象  
(1)创建Book和MyBook **（增强类）** 对象  
2. 在spring核心配置文件中，开启aop操作  
`<aop:aspectj-autoproxy></aop:aspectj-autoproxy>`  
3. 在增强类上面使用注解完成aop操作  
（1）类上面加上`@Aspect`  
（2）方法上面加上  
`@Before(value = "execution(* cn.blinkit.aop.anno.Book.*(..))")`  
`@After(value = "表达式")`  
`@Around(value = "表达式")`等...  

**Book**
```
public class Book {
    public void add() {
        System.out.println("add...注解版本...");
    }
}
```

**MyBook增强类**
```
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
```

**xml配置**
```
<!--开启aop操作-->
    <aop:aspectj-autoproxy></aop:aspectj-autoproxy>

    <!--创建对象-->
    <bean id="book" class="cn.blinkit.aop.anno.Book"></bean>
    <bean id="myBook" class="cn.blinkit.aop.anno.MyBook"></bean>
```


---
###log4j介绍

1. 通过log4j可以看到程序运行过程中更详细的信息  
（1）经常使用log4j查看日志  

2. 使用  
（1）导入log4j的jar包  
`log4j-1.2.17.jar`  
（2）**配置** log4j的文件，放到src下面  
`log4j.properties`  

3. 设置日志级别  
（1）info：可以看到基本信息  
（2）debug：可以看到更详细的信息  
```
log4j.rootLogger=INFO, stdout  

#Console
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
#字体变红色
log4j.appender.stdout.Target=System.err
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout  
log4j.appender.stdout.layout.ConversionPattern=%d [%t] %5p [%c] - %m%n
```