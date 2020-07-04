package com.yqj.test;

import com.yqj.dao.SysRoleDao;
import com.yqj.dao.SysUserDao;
import com.yqj.entity.SysRole;
import com.yqj.entity.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToManyTest {
    @Autowired
    private SysUserDao userDao;

    @Autowired
    private SysRoleDao roleDao;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testAdd(){
        SysUser user = new SysUser();
        user.setUserName("yorick");
        SysRole role = new SysRole();
        role.setRoleName("Admin");
        user.getRoles().add(role);
        role.getUsers().add(user);
        userDao.save(user);
        roleDao.save(role);
    }
}
