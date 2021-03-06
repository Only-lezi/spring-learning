﻿# Spring学习笔记 (七) -- Spring的事务管理

>**目录：**
> - [事务概念](#事务概念) 
> - [Spring事务管理api](#Spring事务管理api) 

---
###事务概念 

1. 什么是事务？  
是对数据库操作的最基本单元，一组操作，要么都成功，有一个失败所有都失败  
2. 事务的特性：  
   4个：原子性、一致性、隔离性、持久性  
   
3. 不考虑隔离性产生读问题  
脏读、不可重复读、虚读、幻读  
隔离性：多个事务之间不会产生影响  

4. 解决读问题
（1）设置隔离级别可以解决

---
###Spring事务管理api 

 1. spring事务管理两种  
第一种：编程式事务管理（一般不用）  
第二种：声明式事务管理  
    （1）基于xml配置文件实现  
    （2）基于注解实现  
  
 2. spring事务管理的api介绍  
事务管理接口：`PlatformTransactionManager`  
（1）spring针对不同的dao层框架，提供接口不同的实现类  
|事务|说明|
|---|---|
|org.springframework.jdbc.datasource.DataSourceTransactionManager|使用Spring JDBC或iBatis进行持久化数据时使用|
|org.springframework.orm.hibernate5.HibernateTransactionManager|使用Hibernate5.0版本进行持久化数据时使用|

**搭建环境**  
1. 创建数据库表，添加数据  

2. 创建service类和dao类,完成注入关系
（1）service层又叫业务逻辑层  
（2）dao层，单纯对数据库操作层，在dao层不添加业务  

3. 如果在操作数据库时，突然出现异常了，下一条语句还没有执行，则数据库数据可能会出现错误。  
（1）小王少了1000后，突然出现异常，小马就不会多1000，钱就丢失了。  

4. 解决：  
（1）添加事务解决，出现异常进行回滚操作  

**声明式事务管理（xml配置）**  
1. 配置文件方式使用aop思想配置  
第一步：配置事务管理器  
第二步：配置事务增强  
第三步：配置切面  


**声明式事务管理（注解配置）**  
第一步：配置事务管理器  
第二步：配置事务注解  
第三步：在要使用事务的方法所在的类上面添加注解  
