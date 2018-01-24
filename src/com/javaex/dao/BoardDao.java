package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;

public class BoardDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public void write(BoardVo boardVo) {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "INSERT INTO board " 
						 + "VALUES (seq_article_no.nextval, ?, ?, ?, ?, sysdate, ?)";

			/* select article_no, writer_no, title, writer, view_count, write_date, content from board; */
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardVo.getWriterNo());
			pstmt.setString(2, boardVo.getTitle());
			pstmt.setString(3, boardVo.getWriter());
			pstmt.setInt(4, boardVo.getViewCount());
			pstmt.setString(5, boardVo.getContent());
			
			int cnt = pstmt.executeUpdate();

			System.out.println(cnt + "건 저장완료");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}

	public List<BoardVo> getList() {
		List<BoardVo> bList = new ArrayList<>();
		BoardVo boardVo;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "SELECT article_no, writer_no, title, writer, view_count, write_date, content "
						 + "FROM board "
						 + "ORDER BY article_no DESC";

			pstmt = conn.prepareStatement(query);
			pstmt.executeQuery();
			rs = pstmt.getResultSet();

			while (rs.next()) {
				boardVo = new BoardVo();
				boardVo.setArticleNo(rs.getInt("article_no"));
				boardVo.setWriterNo(rs.getInt("writer_no"));
				boardVo.setTitle(rs.getString("title"));
				boardVo.setWriter(rs.getString("writer"));
				boardVo.setViewCount(rs.getInt("view_count"));
				boardVo.setDate(rs.getString("write_date"));
				boardVo.setContent(rs.getString("content"));
				bList.add(boardVo);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		return bList;
	}

}