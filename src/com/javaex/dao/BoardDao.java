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
	private final int numByPage = 5;

	public void write(BoardVo boardVo) {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "INSERT INTO board " 
						 + "VALUES (seq_article_no.nextval, ?, ?, 0, sysdate, ?)";

			/* no, title, content, hit, reg_date, user_no */
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContent());
			pstmt.setInt(3, boardVo.getUserNo());
			
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

	public void updateHit(int no) {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "UPDATE board " 
						 + "SET hit = hit + 1 "
						 + "WHERE no = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();

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
	
	public List<BoardVo> getList(int page) {
		List<BoardVo> bList = new ArrayList<>();
		BoardVo boardVo;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "SELECT no, title, content, hit, reg_date, user_no, name " + 
						   "FROM (SELECT rownum rn, board.no no, title, content, hit, " +
						   "to_char(reg_date, 'YY-MM-DD HH:MM') reg_date, user_no, name " + 
						   "      FROM board, users " + 
						   "      WHERE board.user_no = users.no " + 
						   "      ORDER BY board.no DESC) " + 
						   "WHERE ? < rn " + 
						   "AND rn <= ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, numByPage * (page-1));
			pstmt.setInt(2, numByPage * page);
			pstmt.executeQuery();
			rs = pstmt.getResultSet();

			while (rs.next()) {
				boardVo = new BoardVo();
				boardVo.setNo(rs.getInt("no"));
				boardVo.setTitle(rs.getString("title"));
				boardVo.setContent(rs.getString("content"));
				boardVo.setHit(rs.getInt("hit"));
				boardVo.setDate(rs.getString("reg_date"));
				boardVo.setUserNo(rs.getInt("user_no"));
				boardVo.setName(rs.getString("name"));
				
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
	
	public int getMaxPageNum() {
		int maxPageNum = 0;
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "SELECT count(no) cnt " + 
						   "FROM board";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				maxPageNum = rs.getInt("cnt");
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
		
		if(maxPageNum % numByPage == 0)
			return maxPageNum/numByPage;
		else
			return maxPageNum/numByPage + 1;
	}

	public BoardVo getArticle(int no) {
		BoardVo boardVo = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "SELECT no, title, content, hit, user_no "
						 + "FROM board "
						 + "WHERE no = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				boardVo = new BoardVo();
				boardVo.setNo(rs.getInt("no"));
				boardVo.setTitle(rs.getString("title"));
				boardVo.setContent(rs.getString("content"));
				boardVo.setHit(rs.getInt("hit"));
				boardVo.setUserNo(rs.getInt("user_no"));
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
		
		return boardVo;
	}
	
	public List<BoardVo> getSearchList(String kwd) {
		List<BoardVo> bList = new ArrayList<>();
		BoardVo boardVo;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "SELECT board.no no, title, content, hit, to_char(reg_date, 'YY-MM-DD HH:MM') reg_date, user_no, name "
						 + "FROM board, users "
						 + "WHERE board.user_no = users.no "
						 + "AND title LIKE ?"
						 + "ORDER BY board.no DESC";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+kwd+"%");
			pstmt.executeQuery();
			rs = pstmt.getResultSet();

			while (rs.next()) {
				boardVo = new BoardVo();
				boardVo.setNo(rs.getInt("no"));
				boardVo.setTitle(rs.getString("title"));
				boardVo.setContent(rs.getString("content"));
				boardVo.setHit(rs.getInt("hit"));
				boardVo.setDate(rs.getString("reg_date"));
				boardVo.setUserNo(rs.getInt("user_no"));
				boardVo.setName(rs.getString("name"));
				
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
	
	public void modify(BoardVo boardVo) {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "UPDATE board " 
						 + "SET title = ?, content = ? "
						 + "WHERE no = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContent());
			pstmt.setInt(3, boardVo.getNo());
			
			int cnt = pstmt.executeUpdate();

			System.out.println(cnt + "건 갱신완료");

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
	
	public void delete(int no) {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "DELETE FROM board " 
						 + "WHERE no = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			int cnt = pstmt.executeUpdate();

			System.out.println(no + "번 삭제완료");

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
	
}