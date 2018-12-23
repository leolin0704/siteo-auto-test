package com.leo.MySiteTest.dao;

import org.apache.ibatis.annotations.Param;

public interface UserDao {

	void insertByName(@Param("account") String account, @Param("password") String password);

	void deleteByAccount(String account);

	void deleteByRoleName(String roleName);

	void deleteWithoutRole();
}
