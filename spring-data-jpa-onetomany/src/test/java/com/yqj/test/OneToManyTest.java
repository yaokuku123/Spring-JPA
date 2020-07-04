package com.yqj.test;

import com.yqj.dao.CustomerDao;
import com.yqj.dao.LinkManDao;
import com.yqj.entity.Customer;
import com.yqj.entity.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    //保存操作
    @Test
    @Transactional //开启事务
    @Rollback(value = false) //不回滚
    public void testSave(){
        Customer customer = new Customer();
        customer.setCustName("CNU");
        customer.setCustAddress("BeiJing");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("yorick");
        linkMan.setLkmEmail("yaoqijun@outlook.com");
        customer.getLinkmans().add(linkMan);
        linkMan.setCustomer(customer);
        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    //删除操作(可配置级联删除)
    @Test
    @Transactional
    @Rollback(value = false)
    public void testDelete(){
        customerDao.delete(1l);
    }
}
