package com.neo.bean;

/**
 * ������
 * @author Administrator
 *
 */
public class Announcement {
	/**
	 * ���
	 */
	private int id;
	/**
	 * ��������
	 */
	private String content;
	/**
	 * ���淢������
	 */
	
	private String date;
	/**
	 * �������
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
