package cn.blinkit.jdbcTemplate;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Only-lezi
 * @Date: 2017-01-01 17:54
 * @Description:
 */

public class QueryData {

    //原生的JDBC操作
    @Test
    public void testJDBC() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //创建连接
            conn = DriverManager.getConnection("jdbc:mysql:///spring_study?useUnicode=true&characterEncoding=UTF8", "root", "123456");
            //编写sql语句
            String sql = "SELECT * FROM user WHERE id=?";
            //预编译sql语句
            ps = conn.prepareStatement(sql);
            //设置参数值
            ps.setInt(1, 3);
            //执行sql
            rs = ps.executeQuery();
            //遍历结果集
            while (rs.next()) {
                //得到返回结果值
                String username = rs.getString("username");
                String password = rs.getString("password");
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                System.out.println(user);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

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
        int rows = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(rows);
    }

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
        User user = jdbcTemplate.queryForObject(sql, new MyRowMapper(), 1);
        System.out.println(user);
    }

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
        List<User> list = jdbcTemplate.query(sql, new MyRowMapper());
        System.out.println(list);
    }
}
