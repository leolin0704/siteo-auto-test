package com.leo.MySiteTest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leo.MySiteTest.dto.RoleDto;

public interface RoleDao {

	void deleteRolePermissionByRoleName(String roleName);

	void deleteByName(String roleName);

	List<RoleDto> getList();

	void insertByName(String roleName);

	void insertRolePermission(@Param("roleName") String roleName, @Param("permissionName") String permissionName);
}
