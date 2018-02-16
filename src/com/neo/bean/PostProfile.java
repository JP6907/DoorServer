package com.neo.bean;
/**
 * 论坛帖子类
 * @author Administrator
 *
 */
public class PostProfile {
	public int post_id; // 帖子ID (自增长，向表插入记录时不可以指定该值)
	public String post_title; // 帖子标题，储存大小 64KB
	public String post_text; // 帖子内容,储存大小 64KB
	public String post_publisher; // 发布者
	public int post_replynum; // 回复数
	public String post_publishdt; // 发帖日期时间
	public String post_newdt; // 最新日期时间
	public String post_picture1; // 发帖图片1，储存大小 64KB
	public String post_picture2; // 发帖图片2，储存大小 64KB
	public String post_picture3; // 发帖图片3，储存大小 64KB
	public String post_picture4; // 发帖图片4，储存大小 64KB
	public String post_picture5; // 发帖图片5，储存大小 64KB
	public String post_picture6; // 发帖图片6，储存大小 64KB
	public String post_picture7; // 发帖图片7，储存大小 64KB
	public String post_picture8; // 发帖图片8，储存大小 64KB
	public String post_picture9; // 发帖图片9，储存大小 64KB
	public String post_phone;
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public String getPost_text() {
		return post_text;
	}
	public void setPost_text(String post_text) {
		this.post_text = post_text;
	}
	public String getPost_publisher() {
		return post_publisher;
	}
	public void setPost_publisher(String post_publisher) {
		this.post_publisher = post_publisher;
	}
	public int getPost_replynum() {
		return post_replynum;
	}
	public void setPost_replynum(int post_replynum) {
		this.post_replynum = post_replynum;
	}
	public String getPost_publishdt() {
		return post_publishdt;
	}
	public void setPost_publishdt(String post_publishdt) {
		this.post_publishdt = post_publishdt;
	}
	public String getPost_newdt() {
		return post_newdt;
	}
	public void setPost_newdt(String post_newdt) {
		this.post_newdt = post_newdt;
	}
	public String getPost_picture1() {
		return post_picture1;
	}
	public void setPost_picture1(String post_picture1) {
		this.post_picture1 = post_picture1;
	}
	public String getPost_picture2() {
		return post_picture2;
	}
	public void setPost_picture2(String post_picture2) {
		this.post_picture2 = post_picture2;
	}
	public String getPost_picture3() {
		return post_picture3;
	}
	public void setPost_picture3(String post_picture3) {
		this.post_picture3 = post_picture3;
	}
	public String getPost_picture4() {
		return post_picture4;
	}
	public void setPost_picture4(String post_picture4) {
		this.post_picture4 = post_picture4;
	}
	public String getPost_picture5() {
		return post_picture5;
	}
	public void setPost_picture5(String post_picture5) {
		this.post_picture5 = post_picture5;
	}
	public String getPost_picture6() {
		return post_picture6;
	}
	public void setPost_picture6(String post_picture6) {
		this.post_picture6 = post_picture6;
	}
	public String getPost_picture7() {
		return post_picture7;
	}
	public void setPost_picture7(String post_picture7) {
		this.post_picture7 = post_picture7;
	}
	public String getPost_picture8() {
		return post_picture8;
	}
	public void setPost_picture8(String post_picture8) {
		this.post_picture8 = post_picture8;
	}
	public String getPost_picture9() {
		return post_picture9;
	}
	public void setPost_picture9(String post_picture9) {
		this.post_picture9 = post_picture9;
	}
	public String getPost_phone() {
		return post_phone;
	}
	public void setPost_phone(String post_phone) {
		this.post_phone = post_phone;
	}
	@Override
	public String toString() {
		return "PostProfile [post_id=" + post_id + ", post_title=" + post_title + ", post_text=" + post_text
				+ ", post_publisher=" + post_publisher + ", post_replynum=" + post_replynum + ", post_publishdt="
				+ post_publishdt + ", post_newdt=" + post_newdt + ", post_picture1=" + post_picture1
				+ ", post_picture2=" + post_picture2 + ", post_picture3=" + post_picture3 + ", post_picture4="
				+ post_picture4 + ", post_picture5=" + post_picture5 + ", post_picture6=" + post_picture6
				+ ", post_picture7=" + post_picture7 + ", post_picture8=" + post_picture8 + ", post_picture9="
				+ post_picture9 + ", post_phone=" + post_phone + "]";
	}
	
	
}
