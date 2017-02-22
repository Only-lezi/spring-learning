# Spring学习笔记 (六) -- c3p0连接池 

>**目录：**
> - [Spring配置连接池和dao使用jdbcTemplate](#Spring配置连接池) 
> - [WEB项目流程](#WEB项目流程)

---
###Spring配置连接池和dao使用jdbcTemplate   

1. spring配置c3p0连接池  
（1）导入 jar 包  
`c3p0-0.9.5.2.jar`  
`mchange-commons-java-0.2.11.jar`  

c3p0原始做法：
```
// c3p0 连接池的原始做法
    @Test
    public void testC3P0() throws PropertyVetoException {
        //  c3p0连接池
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql:///spring_study?useUnicode=true&characterEncoding=UTF8");
        dataSource.setUser("root");
        dataSource.setPassword("123456");

        //创建jdbcTemplate对象，设置数据源
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        //创建sql语句
        String sql = "DELETE FROM user where id=?";
        int rows = jdbcTemplate.update(sql,1);
        System.out.println(rows);
    }
```

**现在要把这些代码在spring中配置实现：**

**c3p0的xml配置**
```
<!--配置c3p0连接池-->
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
    <!--注入属性值-->
    <property name="driverClass" value="com.mysql.jdbc.Driver"></property>
        <property name="jdbcUrl" value="jdbc:mysql:///spring_study?useUnicode=true&amp;characterEncoding=UTF8"></property>
        <property name="user" value="root"></property>
        <property name="password" value="123456"></property>
</bean>
```

---
###WEB项目流程 

**项目结构：**
```
cn.blinkit
├──src/
|   ├──spring-c3p0.xml
|   ├──dao/
|   |    ├──UserDao.java
|   ├──service/
|   |    ├──UserService.java
```
1. dao使用jdbcTemplate  
（1）创建service和dao，配置service和dao对象，在service注入dao对象  
（2）创建jdbcTemplate对象，把模板对象注入到dao里面  
（3）在jdbcTemplate里面注入dataSource  
`service->注入dao`  
`dao->注入jdbcTemplate`  
`jdbcTemplate->注入dataSource`  

**xml代码如下：**
```
<!--配置c3p0连接池-->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <!--注入属性值-->
        <property name="driverClass" value="com.mysql.jdbc.Driver"></property>
        <property name="jdbcUrl" value="jdbc:mysql:///spring_study?useUnicode=true&amp;characterEncoding=UTF8"></property>
        <property name="user" value="root"></property>
        <property name="password" value="123456"></property>
    </bean>

    <!--创建jdbcTemplate对象-->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <!--要把dataSource传递到模板对象里面-->
        <property name="dataSource" ref="dataSource"></property>
    </bean>

    <!--创建service和dao对象，在service注入dao对象-->
    <bean id="userDao" class="cn.blinkit.c3p0.UserDao">
        <!--注入jdbcTemplate对象-->
        <property name="jdbaTemplate" ref="jdbcTemplate"></property>
    </bean>
    <bean id="userService" class="cn.blinkit.c3p0.UserService">
        <!--注入dao对象-->
        <property name="userDao" ref="userDao"></property>
    </bean>
```

**UserDao类**
```
public class UserDao {

    //得到JdbcTemplate对象
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //添加操作
    public void add() {
        //创建jdbcTemplate对象
        String sql = "INSERT INTO user values(null,?,?)";
        int rows = jdbcTemplate.update(sql,"张三丰","zhangsanfeng");
        System.out.println(rows);
    }
}
```

**UserService类**
```
public class UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add() {
        userDao.add();
    }
}
```
**测试类**
```
public class UserServiceTest {
    @Test
    public void testAdd() {
        ApplicationContext context = new ClassPathXmlApplicationContext("cn/blinkit/c3p0/spring-c3p0.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.add();
    }
}
```
