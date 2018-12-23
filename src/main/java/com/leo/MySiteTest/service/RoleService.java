package com.leo.MySiteTest.service;

import java.util.List;

import com.leo.MySiteTest.dao.AdminUserRoleDao;
import com.leo.MySiteTest.dao.RoleDao;
import com.leo.MySiteTest.dao.UserDao;
import com.leo.MySiteTest.dto.RoleDto;

public class RoleService extends BaseService {

	UserDao userDao = CreateDao(UserDao.class);
	RoleDao roleDao = CreateDao(RoleDao.class);
	AdminUserRoleDao adminUserRoleDao = CreateDao(AdminUserRoleDao.class);

	public void deleteByName(String roleName) {
		roleDao.deleteByName(roleName);

		this.getSession().commit();
	}

	public void deleteByName(List<String> nameList) {
		for (int i = 0; i < nameList.size(); i++) {
			String name = nameList.get(i);
			roleDao.deleteByName(name);
		}

		this.getSession().commit();
	}

	public List<RoleDto> getList() {
		return roleDao.getList();
	}

	public void insertByName(String roleName) {
		roleDao.insertByName(roleName);
		this.getSession().commit();
	}

	public void insert(String roleName, List<String> permissionNameList) {

		roleDao.insertByName(roleName);

		for (int i = 0; i < permissionNameList.size(); i++) {
			String permissionName = permissionNameList.get(i);
			roleDao.insertRolePermission(roleName, permissionName);
		}

		this.getSession().commit();
	}

	public void initRole(String roleName, List<String> permissionNameList) {
		roleDao.deleteRolePermissionByRoleName(roleName);
		roleDao.deleteByName(roleName);
		this.getSession().commit();

		this.insert(roleName, permissionNameList);
	}

	public void deleteRole(String account, String roleName) {
		adminUserRoleDao.deleteByUserAccount(account);
		adminUserRoleDao.deleteByRoleName(roleName);
		userDao.deleteByAccount(account);
		this.getSession().commit();
		roleDao.deleteRolePermissionByRoleName(roleName);
		roleDao.deleteByName(roleName);
		this.getSession().commit();
	}

	public void initManyRoles(String roleName, List<String> permissionNameList) {
		for (int i = 0; i <= 20; i++) {
			roleDao.deleteRolePermissionByRoleName(roleName + i);
			roleDao.deleteByName(roleName + i);
			this.getSession().commit();

			this.insert(roleName + i, permissionNameList);
		}
	}

}
