package com.leo.MySiteTest.service;

import java.util.List;

import com.leo.MySiteTest.dao.AdminUserRoleDao;
import com.leo.MySiteTest.dao.RoleDao;
import com.leo.MySiteTest.dao.UserDao;

public class UserService extends BaseService {
	UserDao userDao = CreateDao(UserDao.class);
	RoleDao roleDao = CreateDao(RoleDao.class);
	AdminUserRoleDao adminUserRoleDao = CreateDao(AdminUserRoleDao.class);

	RoleService roleService = new RoleService();

	public void insert(String account, String password, String roleName) {
		userDao.insertByName(account, password);
		adminUserRoleDao.insertByAccountAndRoleName(account, roleName);
		this.getSession().commit();
	}

	public void deleteAdminUserRoleByRoleName(String roleName) {
		adminUserRoleDao.deleteByRoleName(roleName);

		this.getSession().commit();
	}

	public void deleteAdminUserRoleByUserAccount(String account) {
		adminUserRoleDao.deleteByUserAccount(account);

		this.getSession().commit();
	}

	public void deleteByAccount(String UserName) {
		userDao.deleteByAccount(UserName);

		this.getSession().commit();
	}

	public void InitUser(String account, String roleName, String password, List<String> permissionNameList) {

		adminUserRoleDao.deleteByUserAccount(account);
		adminUserRoleDao.deleteByRoleName(roleName);
		userDao.deleteByAccount(account);
		this.getSession().commit();

		roleService.initRole(roleName, permissionNameList);

		this.insert(account, password, roleName);
	}

	public void initManyUsers(String account, String roleName, String password, List<String> permissionNameList) {
		adminUserRoleDao.deleteByRoleName(roleName);
		userDao.deleteWithoutRole();
		this.getSession().commit();
		roleService.initRole(roleName, permissionNameList);
		this.getSession().commit();

		for (int i = 0; i <= 20; i++) {

			String newAccount = account + i;

			adminUserRoleDao.deleteByUserAccount(newAccount);
			userDao.deleteByAccount(newAccount);
			this.getSession().commit();

			this.insert(newAccount, password, roleName);
		}
	}

	public void deleteManyUsers(String account) {

		for (int i = 0; i <= 20; i++) {

			String newAccount = account + i;

			adminUserRoleDao.deleteByUserAccount(newAccount);
			userDao.deleteByAccount(newAccount);
			this.getSession().commit();

		}
	}

}
