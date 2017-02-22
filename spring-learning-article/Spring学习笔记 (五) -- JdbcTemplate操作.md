# Spring学习笔记 (五) -- JdbcTemplate操作 

>**目录：**
> - [spring的jdbcTemplate操作](#spring的jdbcTemplate操作) 
> - [查询返回某个值](#查询返回某个值)
> - [查询返回对象](#查询返回对象)
> - [查询返回List集合](#查询返回List集合)

---
###spring的jdbcTemplate操作

1. spring框架是一站式框架  
（1）针对javaEE三层，每一层都有解决技术  
（2）在dao层，使用jdbcTemplate  

2. spring对不同的持久化层技术都进行封装  
|ORM持久化技术|模板类|
|---|:---|
|JDBC |`org.springframework.jdbc.core.JdbcTemplate`|
|Hibernate5.0 | `org.springframework.orm.hibernate5.HibernateTemplate`|
|MyBatis | `org.springframework.orm.ibatis.SqlMapClientTemplate`|
|JPA |`org.springframework.orm.jpa.JpaTemplate`|

3. JdbcTemplate使用和dbutils使用都很相似，都对数据库进行CRUD操作

 - **导入JdbcTemplate的 jar 包**  
    - `spring-jdbc-4.3.1.RELEASE.jar`
    - `spring-tx-4.3.1.RELEASE.jar`
    - 和MySQL的jar包：`mysql-connector-java-5.1.40-bin.jar`
 - 创建对象，设置数据库信息  
 - 创建jdbcTemplate对象，设置数据源  
 - 调用jdbcTemplate对象里面的方法实现操作  

**增删改都是调用的update方法，**

（1）增加
```
//1. 添加操作
    @Test
    public void add() {
        //创建对象，设置数据库信息
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///spring_study?useUnicode=true&characterEncoding=UTF8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        //创建jdbcTemplate对象，设置数据源
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        //调用jdbcTemplate对象里面的方法实现操作
        //创建sql语句
        String sql = "insert into user values(null,?,?)";  //MySQL中设置了id的自增
        int rows = jdbcTemplate.update(sql, "lisi","250");
        System.out.println(rows);
    }
```
（2）修改
```
//创建sql语句
        String sql = "update user set username=?,password=? where id=?";
        int rows = jdbcTemplate.update(sql, "李四","123456",1);
        System.out.println(rows);
```
（3）删除
```
//创建sql语句
        String sql = "DELETE FROM user WHERE id=?";
        int rows = jdbcTemplate.update(sql, 1);
        System.out.println(rows);
```
**（4）查询</span>**

 - jdbcTemplate实现查询，有接口RowMapper
 - JdbcTemplate针对这个接口没有提供实现类，要得到不同的类型数据需要自己进行数据封装

###查询返回某一个值（数据条数） (一):  
`queryForObject(String sql,Class<T> requiredType)`  
第一个参数是SQL语句  
第二个参数是返回类型的class（如果是int类型的，则是`Integer.class`）  
```
//1.查询表中记录(返回某一个值)
    @Test
    public void testQueryCount() {
        //创建对象，设置数据库信息
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///spring_study?useUnicode=true&characterEncoding=UTF8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        //创建jdbcTemplate对象，设置数据源
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select count(*) from user";
        int rows = jdbcTemplate.queryForObject(sql,Integer.class);
        System.out.println(rows);
    }
```

###查询返回对象（二）：
`queryForObject(String sql,RowMapper<T> rowMapper,Object... args)`  
第一个参数是sql语句  
第二个参数是RowMapper接口  
第三个参数是可变参数  

*由于方法中接口（`RowMapper<T>`）需要自己实现*  
```
public class MyRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int num) throws SQLException {
        //1.从结果集里面把数据得到
        int id = rs.getInt("id");
        String username = rs.getString("username");
        String password = rs.getString("password");

        //2.把得到数据封装到对象里面
        User user = new User(id, username, password);
        return user;
    }
}
```
User
```
public class User {
    private int id;
    private String username;
    private String password;

    public User() {

    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
```

测试代码
```
//2.查询返回对象
@Test
    public void testQueryObject() {
        //创建对象，设置数据库信息
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///spring_study?useUnicode=true&characterEncoding=UTF8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        //创建jdbcTemplate对象，设置数据源
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM user WHERE id=?";
        //第二个参数是接口RowMapper，需要自己写类来实现这个接口，自己做数据的封装
        User user = jdbcTemplate.queryForObject(sql,new MyRowMapper(), 1);
        System.out.println(user);
    }
```

###查询返回 list 集合（三）：

`List<T> query(String sql,RowMapper<T> rowMapper,Object... args)`  
第一个参数是sql语句  
第二个参数是RowMapper接口，需要自己写类来实现这个接口  

测试代码（需要上面写的User类和MyRowMapper类）  
```
//2.查询返回list
    @Test
    public void testQueryList() {
        //创建对象，设置数据库信息
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///spring_study?useUnicode=true&characterEncoding=UTF8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        //创建jdbcTemplate对象，设置数据源
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM user";
        List<User> list = jdbcTemplate.query(sql,new MyRowMapper());
        System.out.println(list);
    }
```
