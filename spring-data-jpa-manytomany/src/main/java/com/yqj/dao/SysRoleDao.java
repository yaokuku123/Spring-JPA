package com.yqj.dao;

import com.yqj.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysRoleDao extends JpaRepository<SysRole,Long>, JpaSpecificationExecutor<SysRole> {
}
