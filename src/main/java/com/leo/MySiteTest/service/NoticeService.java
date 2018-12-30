package com.leo.MySiteTest.service;

import com.leo.MySiteTest.dao.NoticeDao;

public class NoticeService extends BaseService {

	NoticeDao noticeDao = CreateDao(NoticeDao.class);

	public void insertByTitle(String title) {
		noticeDao.insertByName(title);
		this.getSession().commit();
	}

	public void insertManyTitle(String title) {
		for (int i = 0; i <= 20; i++) {
			String newTitle = title + i;
			noticeDao.insertByName(newTitle);
			this.getSession().commit();
		}
	}

	public void deleteManyTitle(String title) {

		for (int i = 0; i <= 20; i++) {

			String newTitle = title + i;

			noticeDao.deleteManyTitle(newTitle);
			this.getSession().commit();

		}
	}
}
