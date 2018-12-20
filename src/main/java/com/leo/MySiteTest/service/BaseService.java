package com.leo.MySiteTest.service;

import org.apache.ibatis.session.SqlSession;

import com.leo.MySiteTest.tool.DBTools;

public abstract class BaseService {

	public BaseService() {
		this.session = DBTools.getSession();
	}

	private SqlSession session;

	public SqlSession getSession() {
		return session;
	}

	protected <T extends Object> T CreateDao(Class<T> type) {
		return this.session.getMapper(type);
	}

}
