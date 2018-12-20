package com.leo.Practise;

import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.leo.MySiteTest.dto.RoleDto;
import com.leo.MySiteTest.service.RoleService;

public class TestDB {

	private RoleService roleService;

	@Test
	public void TestDBConnect() {
		List<RoleDto> result = roleService.getList();
		Assert.assertNotNull(result);
	}

	@Before
	public void beforeMethod() {
		roleService = new RoleService();
	}

	@After
	public void afterMethod() {

	}
}
