package com.javaex.dao;

import java.util.List;

import com.javaex.vo.GuestVo;

public class GuestBookDaoTest {
	public static void main(String[] args) {
		GuestBookDao dao = new GuestBookDao();
		GuestVo vo = new GuestVo(1, "a", "a", "a", "2001/01/01 01:01:01");
	//	dao.insert(vo);	
		
		List<GuestVo> gList = dao.getList();
		
		for(GuestVo gVo : gList) {
			System.out.println(gVo.toString());
		}
	}
}
