package com.yqj.jpa.test;

import com.yqj.jpa.domain.Customer;
import com.yqj.jpa.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: JpaTest
 * Author: yaoqijun
 * Date: 2020/6/28 14:12
 */
public class JpaTest {

    //实现保存操作
    @Test
    public void TestPersist(){
        //1.创建实体类管理器工厂对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //2.创建实体类管理器
        EntityManager entityManager = factory.createEntityManager();
        //3.开启事务
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        //4.保存操作
        Customer customer = new Customer();
        customer.setCustName("tom");
        customer.setCustAddress("ShangHai");
        customer.setCustIndustry("CUN");
        entityManager.persist(customer);
        //5.提交事务
        tx.commit();
        //6.释放资源
        entityManager.close();
        factory.close();
    }

    //使用工具类保存操作
    @Test
    public void testPersistByUtils(){
        //定义对象
        Customer customer = new Customer();
        customer.setCustName("alice");
        customer.setCustAddress("HuBei");

        EntityManager entityManager = null;
        EntityTransaction tx = null;
        try {
            //1.创建实体类管理器
            entityManager = JpaUtils.getEntityManager();
            //2.开启事务
            tx = entityManager.getTransaction();
            tx.begin();
            //3.保存操作
            entityManager.persist(customer);
            //4.提交事务
            tx.commit();
        } catch (Exception e) {
            //回滚事务
            tx.rollback();
            e.printStackTrace();
        } finally {
            //5.释放资源
            entityManager.close();
        }
    }

    //修改
    @Test
    public void testMerge(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        //i 先根据id查到相应数据
        //ii 修改数据
        Customer customer = entityManager.find(Customer.class, 1l);
        customer.setCustPhone("110");
        entityManager.merge(customer);

        tx.commit();
        entityManager.close();
    }

    //删除
    @Test
    public void testRemove(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        //i 先根据id查到相应数据
        //ii 删除数据
        Customer customer = entityManager.find(Customer.class, 6l);
        entityManager.remove(customer);

        tx.commit();
        entityManager.close();
    }

    //根据id查询
    /*
        find():
            1.立即加载策略，执行find方法后立即发送sql查询数据库
            2.封装为实体类对象本身
     */
    @Test
    public void testFind(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        Customer customer = entityManager.find(Customer.class, 1l);
        System.out.println(customer);

        tx.commit();
        entityManager.close();
    }

    //根据id查询
    /*
        getReference():
            1.延迟加载策略，执行getReference方法后不会发送sql查询数据库，使用到的时候再发送
            2.封装为实体类的动态代理对象
     */
    @Test
    public void testReference(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        Customer customer = entityManager.getReference(Customer.class, 1l);
        System.out.println(customer);

        tx.commit();
        entityManager.close();
    }
}
