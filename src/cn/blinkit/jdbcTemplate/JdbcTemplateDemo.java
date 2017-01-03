package cn.blinkit.jdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.beans.PropertyVetoException;

/**
 * @Author: Only-lezi
 * @Date: 2017-01-01 16:51
 * @Description: Spring中的JdbcTemplate
 */

public class JdbcTemplateDemo {
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
        int rows = jdbcTemplate.update(sql, "lisi", "250");
        System.out.println(rows);
    }

    //2. 修改操作
    @Test
    public void upadte() {
        //创建对象，设置数据库信息
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///spring_study?useUnicode=true&characterEncoding=UTF8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        //创建jdbcTemplate对象，设置数据源
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        //创建sql语句
        String sql = "update user set username=?,password=? where id=?";
        int rows = jdbcTemplate.update(sql, "李四", "123456", 1);
        System.out.println(rows);
    }

    //3.删除操作
    @Test
    public void delete() {
        //创建对象，设置数据库信息
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///spring_study?useUnicode=true&characterEncoding=UTF8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        //创建jdbcTemplate对象，设置数据源
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        //创建sql语句
        String sql = "DELETE FROM user where id=?";
        int rows = jdbcTemplate.update(sql, 1);
        System.out.println(rows);
    }

}
