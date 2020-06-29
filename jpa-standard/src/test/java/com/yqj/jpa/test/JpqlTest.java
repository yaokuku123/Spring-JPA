package com.yqj.jpa.test;

import com.yqj.jpa.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: JpqlTest
 * Author: yaoqijun
 * Date: 2020/6/29 9:21
 */
/*
    JPQL:其特征与原生SQL语句类似，并且完全面向对象，
    通过类名和属性访问，而不是表名和表的属性。
 */
public class JpqlTest {

    //查询全部
    @Test
    public void testFindAll(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        //1.创建query对象
        //String jpql = "from com.yqj.jpa.domain.Customer";
        String jpql = "from Customer";
        Query query = entityManager.createQuery(jpql);
        //2.查询并得到返回结果
        List resultList = query.getResultList();
        for (Object obj : resultList) {
            System.out.println(obj);
        }

        tx.commit();
        entityManager.close();
    }

    //分页查询
    @Test
    public void testFindPaged(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        //1.创建query对象
        String jpql = "from Customer";
        Query query = entityManager.createQuery(jpql);
        //2.赋值参数
        query.setFirstResult(0);
        query.setMaxResults(2);
        //3.查询并得到返回结果
        List resultList = query.getResultList();
        for (Object obj : resultList) {
            System.out.println(obj);
        }

        tx.commit();
        entityManager.close();
    }

    //条件查询
    @Test
    public void testCondition(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        //1.创建query对象
        String jpql = "from Customer where custName like ?";
        Query query = entityManager.createQuery(jpql);
        //2.赋值参数
        query.setParameter(1,"%o%");
        //3.查询并得到返回结果
        List resultList = query.getResultList();
        for (Object obj : resultList) {
            System.out.println(obj);
        }

        tx.commit();
        entityManager.close();
    }

    //排序查询，倒序(根据id)
    @Test
    public void testOrder(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        //1.创建query对象
        String jpql = "from Customer order by custId desc";
        Query query = entityManager.createQuery(jpql);
        //2.查询并得到返回结果
        List resultList = query.getResultList();
        for (Object obj : resultList) {
            System.out.println(obj);
        }

        tx.commit();
        entityManager.close();
    }

    //统计查询
    @Test
    public void testCount(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        //1.创建query对象
        String jpql = "select count(custId) from Customer";
        Query query = entityManager.createQuery(jpql);
        //2.查询并得到返回结果
        Object result = query.getSingleResult();
        System.out.println(result);

        tx.commit();
        entityManager.close();
    }
}
