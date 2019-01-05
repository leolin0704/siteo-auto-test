package com.leo.MySiteTest.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.leo.MySiteTest.dto.NoticeDto;

public interface NoticeDao {

	void insert(NoticeDto title);

	void insertByName(String title);

	void deleteManyTitle(String newTitle);

	void insertByTime(@Param("title") String title, @Param("time") Date time);

	void deleteAllNotice();
}
