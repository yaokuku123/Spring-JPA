package com.yqj.test;

import com.yqj.dao.CustomerDao;
import com.yqj.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest {

    @Autowired
    private CustomerDao customerDao;

    //根据客户名并且根据地址精确查询
    @Test
    public void testFindByNameAndAddress(){
        Specification spec = new Specification() {
            /**
             * root	    ：Root接口，代表查询的根对象，可以通过root获取实体中的属性
             * query	：代表一个顶层查询对象，用来自定义查询
             * cb		：用来构建查询，此对象里有很多条件方法
             */
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                Expression custName = root.get("custName").as(String.class);
                Expression custAddress = root.get("custAddress").as(String.class);
                Predicate p1 = cb.equal(custName,"yorick");
                Predicate p2 = cb.equal(custAddress, "BeiJing");
                Predicate predicate = cb.and(p1, p2);
                return predicate;
            }
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    //根据客户名模糊查询
    @Test
    public void testFindByName(){
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                Expression custName = root.get("custName").as(String.class);
                Predicate predicate = cb.like(custName, "%e%");
                return predicate;
            }
        };
        List customers = customerDao.findAll(spec);
        for (Object customer : customers) {
            System.out.println(customer);
        }
    }

    //按id排序查询
    @Test
    public void testOrder(){
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                Expression custIndustry = root.get("custIndustry").as(String.class);
                Predicate predicate = cb.equal(custIndustry, "CUN");
                return predicate;
            }
        };
        Sort sort = new Sort(Sort.Direction.DESC,"custId");
        List customers = customerDao.findAll(spec, sort);
        for (Object customer : customers) {
            System.out.println(customer);
        }
    }

    //分页查询
    @Test
    public void testPage(){
        Specification spec = null;
        Pageable pageable = new PageRequest(0,2);
        Page<Customer> customers = customerDao.findAll(spec, pageable);
        for (Object customer : customers) {
            System.out.println(customer);
        }
    }

}
