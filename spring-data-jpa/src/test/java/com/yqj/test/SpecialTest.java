package com.yqj.test;

import com.yqj.dao.CustomerDao;
import com.yqj.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecialTest {

    @Autowired
    private CustomerDao customerDao;

    //使用jpql语句操作
    //1.使用jpql的方式查询全部客户
    @Test
    public void testJpqlFindAll(){
        List<Customer> customers = customerDao.findAll();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    //2.使用jpql的方式按照姓名查询客户
    @Test
    public void testJpqlFindByName(){
        Customer customer = customerDao.findCustomer("yorick");
        System.out.println(customer);
    }

    //3.使用jpql的方式更新客户
    @Test
    @Transactional
    @Rollback(value = false)
    public void testJpqlUpdate(){
        customerDao.updateCustomer(2l,"jerry");
    }

    //使用sql语句操作
    //1.查询全部客户
    @Test
    public void testSqlFindAll(){
        List<Object[]> customers = customerDao.findCustomersSql();
        for (Object[] customer : customers) {
            System.out.println(Arrays.toString(customer));
        }
    }

    //2.条件查询
    @Test
    public void testSqlFindByNameAndAddress(){
        Customer customer = customerDao.findCustomerSql("%yorick%", "BeiJing");
        System.out.println(customer);
    }

    //使用方法命名规则
    //按照Spring Data JPA 定义的规则，查询方法以findBy开头，涉及条件查询时，条件的属性用条件关键字连接，
    // 要注意的是：条件属性首字母需大写。框架在进行方法名解析时，会先把方法名多余的前缀截取掉，然后对剩下部分进行解析

    //1.根据用户名查询
    @Test
    public void testFindByName(){
        Customer customer = customerDao.findByCustName("yorick");
        System.out.println(customer);
    }

    //2.多条件查询
    @Test
    public void testFindByNameAndAddress(){
        Customer customer = customerDao.findByCustNameLikeAndCustAddress("%yorick%","BeiJing");
        System.out.println(customer);
    }
}
