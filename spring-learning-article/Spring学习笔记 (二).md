# Spring学习笔记 (二)  

>**目录：**
> - [属性注入](#属性注入) 
> - [注入对象类型属性](#注入对象类型属性)
> - [p名称空间注入](#p名称空间注入)
> - [注入复杂类型属性(数组、集合)](#注入复杂类型属性)
> - [IOC和DI区别](#IOC和DI区别)

---
### 属性注入

**1. 创建对象的时候，向类里面属性里面设置值**  
**2. 属性注入的方式介绍（三种方式）**  
（1）使用set方法注入  
（2）使用有参数的构造注入  
~~（3）使用接口注入（spring不支持）~~  

**3.在spring里面，只支持两种方法注入**  
（1）set方法注入  
`name属性值：类里面定义的属性名称`  
`value属性：设置具体的值`  

**bean类**
```
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
```

**xml配置**
```
<!--使用set方法注入属性-->
    <bean id="userSet" class="cn.blinkit.set.User">
        <!--注入属性值
            name属性值：类里面定义的属性名称
            value属性：设置具体的值
        -->
        <property name="username" value="小芳"></property>
    </bean>
```

**测试**
```
public class UserTest {
    @Test
    public void testUser() {
        //1.加载spring配置文件，根据创建对象
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        //2.得到配置创建的对象
        User user = (User) context.getBean("userSet");
        user.print();
    }
}
```

（2）有参数构造注入  

**bean类**
```
public class PropertyDemo1 {
    private String username;

    public PropertyDemo1(String username) {
        this.username = username;
    }

    public void add() {
        System.out.println("PropertyDemo1..:" + username);
    }
}
```  

**xml配置**  
```
<!--使用有参数的构造注入属性-->
    <bean id="propertyDemo1" class="cn.blinkit.property.PropertyDemo1">
        <!--使用有参构造注入-->
        <constructor-arg name="username" value="张三"></constructor-arg>
    </bean>
```

**测试**
```
public class PropertyDemo1Test {
    @Test
    public void testPropertyDemo1() {
        //1.加载spring配置文件，根据创建对象
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        //2.得到配置创建的对象
        PropertyDemo1 propertyDemo1 = (PropertyDemo1) context.getBean("propertyDemo1");
        propertyDemo1.add();
    }
}
```

### 注入对象类型属性（重点）

 1. 创建service类和dao类  
（1）在service得到dao的对象  
 2. 具体实现过程  
（1）在service里面把dao作为类型属性  
（2）在service里面生成dao类型属性的set方法  
（3）配置文件中注入关系（ref为配置dao的id值）  

**dao层代码**
```
public class UserDao {

    public void add() {
        System.out.println("dao......");
    }
}
```
**service层代码**
```
public class UserService {
    //1.定义dao类型属性
    private UserDao userDao;

    //2.生成set方法
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add() {
        System.out.println("service......");
        //在service里面得到dao类对象，才能调用dao里面的方法
        userDao.add();
    }
}
```
**xml中配置**
```
<!--注入对象类型的属性-->
    <!--1.配置service和dao对象-->
    <bean id="userDao" class="cn.blinkit.object.UserDao"></bean>

    <bean id="userService" class="cn.blinkit.object.UserService">
        <!--注入dao对象
            name属性值：service类里面属性名称
            注：这里就不能写value属性，因为刚才是字符串，现在是对象
            写ref属性：dao配置bean标签中id值
        -->
        <property name="userDao" ref="userDao"></property>
    </bean>
```
**测试代码**
```
public class ObjectTest {
    @Test
    public void testUserService() {
        //1.加载spring配置文件，根据创建对象
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        //2.得到配置创建的对象
        UserService userService = (UserService) context.getBean("userService");
        userService.add();
    }
}
```
---

### p名称空间注入

**bean类**
```
public class Person {
    private String pname;

    public void setPname(String pname) {
        this.pname = pname;
    }
    public void print() {
        System.out.println("person...." + pname);
    }
}
```

**xml配置**
```
<!--首先需要在beans里面加入下面的名称空间-->
xmlns:p="http://www.springframework.org/schema/p"

<!--p名称空间注入-->
    <bean id="person" class="cn.blinkit.pNameSpace.Person" p:pname="李四"></bean>
```

**测试代码**
```
public class PersonTest {
    @Test
    public void testPerson() {
        //1.加载spring配置文件，根据创建对象
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        //2.得到配置创建的对象
        Person person = (Person) context.getBean("person");
        person.print();
    }
}
```

---
### 注入复杂类型属性(数组、集合)

**bean类**
```
public class ComplexType {

    private String[] arrs;
    private List<String> list;
    private Map<String, String> map;
    private Properties properties;

    public void setArrs(String[] arrs) {
        this.arrs = arrs;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void print() {
        System.out.println("arrs: " + arrs.toString());
        System.out.println("list: " + list);
        System.out.println("map: " + map);
        System.out.println("properties: " + properties);
    }
}
```

**xml配置**
```
<!--注入复杂类型属性值-->
    <bean id="complexType" class="cn.blinkit.complexType.ComplexType">
        <!--数组-->
        <property name="arrs" >
            <list>
                <value>张三</value>
                <value>李四</value>
                <value>王五</value>
            </list>
        </property>

        <!--list-->
        <property name="list" >
            <list>
                <value>list1</value>
                <value>list2</value>
                <value>list3</value>
            </list>
        </property>

        <!--map-->
        <property name="map" >
            <map>
                <entry key="aa" value="小明"></entry>
                <entry key="bb" value="小李"></entry>
                <entry key="cc" value="小王"></entry>
            </map>
        </property>

        <!--properties-->
        <property name="properties">
            <props>
                <prop key="driverclass">com.mysql.jdbc.Driver</prop>
                <prop key="username">root</prop>
                <prop key="password">123456</prop>
            </props>
        </property>
    </bean>
```

**测试类**
```
public class ComplexTypeTest {
    @Test
    public void testComplexType() {
        //1.加载spring配置文件，根据创建对象
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        //2.得到配置创建的对象
        ComplexType complexType = (ComplexType) context.getBean("complexType");
        complexType.print();
    }
}
```
---

### IOC和DI区别

（1）IOC：控制反转，把对象创建交给spring进行配置（创建对象）  
（2）DI：依赖注入，向类里面的属性中设置值（注入属性）  
（3）关系：依赖注入不能单独存在，需要在IOC的基础之上  

