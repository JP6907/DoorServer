package com.neo.bean;

/**
 * 公告类
 * @author Administrator
 *
 */
public class Announcement {
	/**
	 * 编号
	 */
	private int id;
	/**
	 * 公告内容
	 */
	private String content;
	/**
	 * 公告发布日期
	 */
	
	private String date;
	/**
	 * 公告标题
	 */
	private String title;
	public String getTitle(){
		return title;
	}
	public void setTitle(String tem){
		this.title = tem;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
