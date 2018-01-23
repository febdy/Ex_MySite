package com.javaex.dao;

import java.util.List;

import com.javaex.vo.UserVo;

public class UserDaoTest {
	public static void main(String[] args) {
		UserDao dao = new UserDao();
		UserVo vo = new UserVo(1, "test", "test", "test", "test");
	//	dao.insert(vo);
	
	/*	List<UserVo> uList = dao.getList();
		
		for(UserVo uVo : uList) {
			System.out.println(uVo.toString());
		}
	*/
		
	}
}
