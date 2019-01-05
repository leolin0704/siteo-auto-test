package com.leo.MySiteTest.service;

import java.util.Date;

import com.leo.MySiteTest.dao.NoticeDao;
import com.leo.MySiteTest.dto.NoticeDto;

public class NoticeService extends BaseService {

	NoticeDao noticeDao = CreateDao(NoticeDao.class);

	public void insertByTitle(String title) {
		NoticeDto notice = new NoticeDto();
		notice.setTitle(title);
		noticeDao.insert(notice);
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

	public void insertByTime(String title, Date time) {
		NoticeDto notice = new NoticeDto();
		notice.setTitle(title);
		notice.setCreateDate(time);
		notice.setLastUpdateDate(time);
		noticeDao.insert(notice);
		this.getSession().commit();
	}

	public void deleteAllNotice() {
		noticeDao.deleteAllNotice();
		this.getSession().commit();
	}
}
