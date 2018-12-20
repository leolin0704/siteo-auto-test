package com.leo.MySiteTest.dao;

import org.apache.ibatis.annotations.Param;

public interface AdminUserRoleDao {

	void deleteByRoleName(String roleName);

	void deleteByUserAccount(String account);

	void insertByAccountAndRoleName(@Param("account") String account, @Param("roleName") String roleName);
}
