package com.javaex.vo;

public class BoardVo {
	private int articleNo;
	private int writerNo;
	private String title;
	private String writer;
	private int viewCount;
	private String date;
	private String content;

	public BoardVo() {

	}

	public BoardVo(int articleNo, int writerNo, String title, String writer, int viewCount, String date,
			String content) {
		this.articleNo = articleNo;
		this.writerNo = writerNo;
		this.title = title;
		this.writer = writer;
		this.viewCount = viewCount;
		this.date = date;
		this.content = content;
	}

	public int getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	public int getWriterNo() {
		return writerNo;
	}

	public void setWriterNo(int writerNo) {
		this.writerNo = writerNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "BoardVo [articleNo=" + articleNo + ", writerNo=" + writerNo + ", title=" + title + ", writer=" + writer
				+ ", viewCount=" + viewCount + ", date=" + date + ", content=" + content + "]";
	}

}
