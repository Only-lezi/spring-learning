# Spring学习笔记（三）

>**目录：**
> - [注解入门](#注解入门)
> - [注解分类介绍](#注解分类介绍)
> - [配置文件和注解混合使用](#配置文件和注解混合使用)

### 注解入门：

 1. 代码里面特殊标记，使用注解可以完成功能
 2. 注解写法：**@注解名称（属性名称=属性值）**
 3. 注解使用在类上面，方法上面和属性上面

---

#### Spring注解开发准备

**1. 导入jar包：**  
（1）导入基本的jar包（6个jar包）  
`参考：Spring学习笔记（一）`  
（2）导入aop的jar包  
`spring-aop-4.x.x.RELEASE.jar`  
 **2. 创建类，创建方法**  
```
 请看下面的5，注意类上面的注解
```
 
 **3.引入约束**  
 (1)需要引入`context`的约束
 `spring-framework-4.3.1.RELEASE\docs\spring-framework-reference\html\xsd-configuration.html`找到context schema  
 
 
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context" xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
        
...

</beans>
```
**4.开启注解扫描**  
```
 <!--
        开启注解扫描：
          （1）到包里面扫描类、方法、属性上面是否有注解
        如果有多个包：
          （1）可以加 逗号 进行分隔
          （2）或者直接加入父包就可以了（建议这么写）
    -->
    <context:component-scan base-package="cn.blinkit.annotation"></context:component-scan>

    <!--
        扫描属性上面的注解(功能没有上面的强大，因此不用！！！)
    -->
    <context:annotation-config></context:annotation-config>
```  

**5.注解创建对象**  
在创建对象的类上面使用注解实现
```
@Component(value = "user")   //value就是id值 相当于<bean id="user" class=""/>
public class User {
    public void print() {
        System.out.println("User类....print");
    }
}
``` 
测试代码：
```
public class UserTest {
    @Test
    public void testUser() {
        //1.加载spring配置文件，根据 配置位置 创建对象
        ApplicationContext context = new ClassPathXmlApplicationContext("/cn/blinkit/annotation/spring-config.xml");
        //2.得到配置创建的对象
        User user = (User) context.getBean("user");
        System.out.println(user);
        user.print();
    }
}
```
### 注解分类介绍：

**1. @Component：组件（作用在类上）**  
    - Spring中提供@Componemt的三个衍生注解：（功能目前来讲是一致的）  
        - **@Controller  ：  WEB层**  
        - **@Service     ：  业务层**  
        - **@Repository  ：  持久层**  
        - 这三个注解是为了让标注类本身的用途清晰，Spring在后续版本会对其增强    

（1）@Component  
（2）@Controller  
（3）@Service  
（4）@Repository  
    **（目前这四个注解功能一样，都创建对象）**    
    
**2.创建对象单实例还是多实例**  
需要在类上面加上@Scope(value = "prototype")  
```
/*@Component(value = "user") */  //value就是id值 相当于<bean id="user" class=""/>
@Service(value = "user")    //跟上面的 @Component(value = "user") 功能一样
@Scope(value = "prototype")    //创建的对象是多实例的  单实例是singleton
public class User {
    public void print() {
        System.out.println("User类....print");
    }
}
```

**3.注解注入属性**  
（1）创建service类，创建dao类，在service得到dao对象  
**@Autowired 注解*  
**@Resource(name="userDao") 注解**  

**UserDao类**
```
@Component(value = "userDao")
public class UserDao {
    public void print() {
        System.out.println("UserDao.....print");
    }
}
```

**UserService类**
```
@Service(value = "userService1")
public class UserService {
    //得到dao对象
    //1.定义dao类型属性，使用注解方式不需要set方法
    //在dao属性上面使用注解完成对象注入
    /*@Autowired*/         //自动装配  根据注入的类对象去找，跟dao里面类配置的value名字无关
    @Resource(name = "userDao")    //name 属性写 注解创建dao对象value值
    private UserDao userDao;

    public void print() {
        System.out.println("UserService......print..");
        userDao.print();
    }
}
```

**测试类**
```
public class UserServiceTest {
    @Test
    public void testUser() {
        //1.加载spring配置文件，根据 配置位置 创建对象
        ApplicationContext context = new ClassPathXmlApplicationContext("/cn/blinkit/annotation/spring-config.xml");
        //2.得到配置创建的对象
        UserService userService = (UserService) context.getBean("userService1");
        System.out.println("这是注解实现Uservice ... " + userService);
        userService.print();
    }
}
```

### 配置文件和注解混合使用(重点)：

**1.创建对象操作是用配置文件方式实现**  
**2. 注入属性的操作使用注解实现**  

BookDao
```
public class BookDao {
    public void book() {
        System.out.println("bookDao......");
    }
}
```

OrdersDao
```
public class OrdersDao {
    public void buy() {
        System.out.println("ordersDao....");
    }
}
```

xml配置
```
<!--开启注解扫描：-->
    <context:component-scan base-package="cn.blinkit.annotation.xmlAndAnno"></context:component-scan>

    <!--配置对象-->
    <bean id="bookDao" class="cn.blinkit.annotation.xmlAndAnno.BookDao"></bean>
    <bean id="ordersDao" class="cn.blinkit.annotation.xmlAndAnno.OrdersDao"></bean>
    <bean id="bookService" class="cn.blinkit.annotation.xmlAndAnno.BookService"></bean>
```  

BookService类
```
public class BookService {
    //得到bookDao和ordersDao
    @Resource(name = "bookDao")
    private BookDao bookDao;

    @Resource(name = "ordersDao")
    private OrdersDao orderDao;

    public void print() {
        System.out.println("BookService......");
        bookDao.book();
        orderDao.buy();
    }
}
```
测试类
```
public class BookServiceTest {
    @Test
    public void testBookService() {
        ApplicationContext context = new ClassPathXmlApplicationContext("cn/blinkit/annotation/xmlAndAnno/xmlAndAnno.xml");
        BookService bookService = (BookService) context.getBean("bookService");
        System.out.println(bookService);
        bookService.print();
    }
}
```  