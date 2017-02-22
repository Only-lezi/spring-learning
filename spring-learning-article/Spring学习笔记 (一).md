# Spring学习笔记 (一)  

>**目录：**
> - [Spring的ioc底层原理](#Spring的ioc底层原理)
> - [Spring入门案例](#Spring入门案例)
> - [Spring的bean管理(xml方式)](#Spring的bean管理)
> - [bean标签的常用属性](#bean标签的常用属性) 

### Spring的ioc底层原理 (降低类之间的耦合度)：
```IOC原理-伪代码
//伪代码
//需要实例化的类
public class UserService{
}

public class UserServlet{
    //得到UserService的对象
    //原始的做法：new 对象();  来创建
    
    //经过spring后
    UserFactory.getService();   //(下面两步的代码调用的)
}
```

第一步：创建xml配置文件，配置要创建的对象类 
```  
<bean id="userService" class="cn.blinkit.UserService"/>
```  

第二步：创建工厂类，使用dom4j解析配置文件+反射  
```  
public class Factory {
    //返回UserService对象的方法
    public static UserService getService() {
        //1.使用dom4j来解析xml文件  
        //根据id值userService，得到id值对应的class属性值
        String classValue = "class属性值";
        //2.使用反射来创建类对象
        Class clazz = Class.forName(classValue)；
        //创建类的对象
        UserService service = clazz.newInstance();
        return service;
    }
}
```  
---
### Spring入门案例

1. **第一步：导入jar包**  
*做spring最基本的功能时，导入核心的4个jar包*  
`spring-bens-4.x.x.RELEASE.jar`    
`spring-core-4.x.x.RELEASE.jar`  
`spring-context-4.x.x.RELEASE.jar`  
`spring-expression-4.x.x.RELEASE.jar`(SpEL)   
*同时还需要导入支持日志输出的jar包*  
`commons-logging-1.2.jar`  
`log4j-1.2.x.jar`  

2.**创建类，在类里面创建方法**  

```
public class User {
    //spring默认创建对象的时候会调用无参的构造方法，如果没有无参的构造，会出现异常
    public User() {
    }
    
    public void add() {
        System.out.println("Hello World...");
    }
}
```  

3.**创建spring配置文件，配置创建类**    
    1)spring核心配置文件名称和位置不是固定的，建议放在src下面，官方建议`applicationContext.xml`  
    2)引入schema约束和配置对象创建  
`在压缩包：spring-framework-4.3.1.RELEASE\docs\spring-framework-reference\html`

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--ioc入门 配置对象创建-->
    <bean id="user" class="cn.blinkit.User"></bean>

</beans>
```

4.**创建测试**
```
public class UserTest {
    @Test
    public void testUser() {
        //1.加载spring配置文件，根据创建对象
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2.得到配置创建的对象
        User user = (User)context.getBean("user");
        System.out.println(user);
        user.add();
    }
}
```  

---  
### Spring的bean管理（xml方式）

#### bean实例化的方式  

**1.在spring里面通过配置文件创建对象**  

**2.bean实例化三种方式实现**  
第一种：使用类的 *无参数构造方法* 创建（重点）  
    *参照上面的 [Spring入门案例](#rumen)*  
    **注：类里面如果没有无参数的构造方法，会出现异常**  
    
第二种：使用静态工厂创建 **(开发中一般不用)**  
（1）创建静态的方法，返回类的对象  

**Bean：**  
```
public class Bean {
    public Bean() {
    }

    public void add() {
        System.out.println("bean的add方法....");
    }
}
``` 

**BeanFactory：**
```
public class BeanFactory {
    //静态的方法，返回bean的对象
    public static Bean getBean() {
        return new Bean();
    }
}
```

**xml配置：**  
```
<!--demo2   使用静态工厂创建对象-->
    <bean id="bean" class="cn.blinkit.demo2.BeanFactory" factory-method="getBean"></bean>
```  

**测试代码**  
```
public class BeanTest {
    @Test
    public void beanTest() {
        //1.加载spring配置文件，根据创建对象
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        //2.得到配置创建的对象
        Bean bean = (Bean)context.getBean("bean");
        System.out.println(bean);
        bean.add();
    }
}
```

第三种：使用实例工厂创建 **(开发中一般不用)**  
（2）创建不是静态的方法  
**Bean和上面的一样**  

**BeanFactory：**  
```
public class BeanFactory2 {
    //普通方法，返回Bean对象
    public Bean getBean() {
        return new Bean();
    }
}
```

**xml配置**
```
<!--demo2   使用实例工厂创建对象-->
    <!-- 创建工厂对象 -->
    <bean id="BeanFactory2" class="cn.blinkit.demo2.BeanFactory2"></bean>
    <bean id="bean2" factory-bean="BeanFactory2" factory-method="getBean"></bean>
```

**测试：**
```
public class BeanTest2 {
    @Test
    public void beanTest2() {
        //1.加载spring配置文件，根据创建对象
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        //2.得到配置创建的对象
        Bean bean = (Bean)context.getBean("bean2");
        System.out.println(bean);
        bean.add();
    }
}
```
---
### Bean标签常用属性

**1. id属性**

 - 起的名字，id属性值名称任意命名  
 - id属性值不能包含特殊符号
 - 根据id得到配置对象

**2. class属性**  

 - 创建对象所在类的全路径

**3. name属性**  

 - 功能和id属性一样，id属性值不能包含特殊符号，但是在name值里面可以包含特殊符号


**4. scope属性**  

 - **singleton： spring默认的，单例的**
    - scope="singleton"
 - **prototype：多例的**
    - scope="prototype"
 - request：创建对象把对象放到request域里面
    - WEB项目中，Spring创建一个Bean的对象，将对象存入到request域中。
 - session：创建对象把对象放到session域里面
    - WEB项目中，Spring创建一个Bean的对象，将对象存入到session域中。
 - globalSession：*全局session(单点登录)*   创建对象把对象放到globalSession域里面
    - WEB项目中，**应用在Porlet环境**，如果没有Porlet环境那么globalSession相当于session。
