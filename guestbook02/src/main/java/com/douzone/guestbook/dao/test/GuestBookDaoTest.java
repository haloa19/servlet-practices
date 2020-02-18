package com.douzone.guestbook.dao.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.douzone.guestbook.dao.GuestBookDao;
import com.douzone.guestbook.vo.GuestBookVo;

public class GuestBookDaoTest {
	static SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static Date date = new Date();

	public static void main(String[] args) {
		insertTest();
		selectTest();
//		deleteTest();
//		selectTest();

	}
	
	public static void insertTest() {
		GuestBookVo vo = new GuestBookVo();
		vo.setName("둘리");
		vo.setContents("안녕, 나는 둘리야!!");
		vo.setPassword("1212");
		vo.setNgDate(format1.format(date));
		new GuestBookDao().insert(vo);
	}
	
	public static void selectTest() {
		List<GuestBookVo> list = new GuestBookDao().findAll();
		for(GuestBookVo vo : list) {
			System.out.println(vo);
		}
	}
	
	public static void deleteTest() {
		GuestBookDao guestbook = new GuestBookDao();
		guestbook.delete(null);
	}

}
