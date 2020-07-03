package com.yqj.dao;


import com.yqj.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {
    //@Query 使用jpql的方式查询。
    @Query(value = "from Customer")
    List<Customer> findAll();

    //@Query 使用jpql的方式查询。?1代表参数的占位符，其中1对应方法中的参数索引
    @Query(value = "from Customer where custName=?1")
    Customer findCustomer(String name);

    //在使用 @Query 的同时，用 @Modifying 来将该操作标识为修改查询
    @Query(value = "update Customer set custName=?2 where custId=?1")
    @Modifying
    void updateCustomer(Long id,String name);

    //sql语句查询全部客户 nativeQuery : 使用本地sql的方式查询
    @Query(value = "select * from cst_customer",nativeQuery = true)
    List<Object[]> findCustomersSql();

    //sql语句根据用户名字模糊匹配，并且根据地址精准匹配
    @Query(value = "select * from cst_customer where cust_name like ? and cust_address=?",nativeQuery = true)
    Customer findCustomerSql(String name,String address);

    //方法命名方式查询（根据客户名称查询客户）
    Customer findByCustName(String name);

    //方法命名方式查询（根据客户名称模糊查询并且地址精确查询客户）
    Customer findByCustNameLikeAndCustAddress(String name,String address);
}
