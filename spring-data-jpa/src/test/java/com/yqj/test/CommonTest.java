package com.yqj.test;

import com.yqj.dao.CustomerDao;
import com.yqj.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CommonTest {

    @Autowired
    private CustomerDao customerDao;

    //1.保存客户 调用save(obj)方法
    @Test
    public void testSave(){
        Customer customer = new Customer();
        customer.setCustName("bob");
        customer.setCustAddress("ChongQing");
        customerDao.save(customer);
    }

    //2.修改客户：调用save(obj)方法
    //若此对象存在id属性，则会先根据id查询再更新。否则若不存在id属性即为保存操作
    @Test
    public void testUpdate(){
        Customer customer = new Customer();
        customer.setCustId(4l);
        customer.setCustName("bob");
        customer.setCustAddress("HeBei");
        customerDao.save(customer);
    }

    //3.根据id删除：调用delete(id)方法
    @Test
    public void testDelete(){
        customerDao.delete(4l);
    }

    //4.根据id查询：调用findOne(id)方法
    @Test
    public void testFindOne(){
        Customer customer = customerDao.findOne(1l);
        System.out.println(customer);
    }

    //5.根据id查询：调用getOne(id)方法,延迟加载，底层调用getReference()
    @Test
    @Transactional
    public void testGetOne(){
        Customer customer = customerDao.getOne(1l);
        System.out.println(customer);
    }

    //6.查询统计信息，调用count()
    @Test
    public void testCount(){
        long count = customerDao.count();
        System.out.println(count);
    }

    //7.根据id查询客户是否存在，调用exists(id)
    @Test
    public void testExists(){
        boolean exists = customerDao.exists(1l);
        System.out.println(exists);
    }

}
