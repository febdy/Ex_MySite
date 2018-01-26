package com.javaex.dao;

import java.util.List;

import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

public class BoardDaoTest {
	public static void main(String[] args) {
		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo();
		vo.setNo(1);
		vo.setTitle("test");
		vo.setContent("test");
		vo.setUserNo(1);
	//	dao.insert(vo);		
		
		List<BoardVo> bList = dao.getList();
		
		for(BoardVo bVo : bList) {
			System.out.println(bVo.toString());
		}
		
	}
}
