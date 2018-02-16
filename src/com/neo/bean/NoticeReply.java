package com.neo.bean;

import java.io.Serializable;
/**
 * 公告回复类
 * @author Administrator
 *
 */
public class NoticeReply implements Serializable{
	private static final long serialVersionUID = 8257429880277711117L;
	//id
	private int replyId;
	//回复的公告的id
	private int replyNoticeId;
	//回复判断，判断是否回复搂主，还是回复回复
	private Integer replyJudge; 
	//回复日期
	private String replyDateTime;
	//回复者
	private String replyResponder; 
	//
	private String replyPublisher; 
	//回复的楼层
	private int replyFloor;
	//回复内容
	private String replyText; 
	
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public int getReplyNoticeId() {
		return replyNoticeId;
	}
	public void setReplyNoticeId(int tem) {
		this.replyNoticeId = tem;
	}
	public Integer getReplyJudge() {
		return replyJudge;
	}
	public void setReplyJudge(Integer replyJudge) {
		this.replyJudge = replyJudge;
	}
	public String getReplyDateTime() {
		return replyDateTime;
	}
	public void setReplyDateTime(String replyDateTime) {
		this.replyDateTime = replyDateTime;
	}
	public String getReplyResponder() {
		return replyResponder;
	}
	public void setReplyResponder(String replyResponder) {
		this.replyResponder = replyResponder;
	}
	public String getReplyPublisher() {
		return replyPublisher;
	}
	public void setReplyPublisher(String replyPublisher) {
		this.replyPublisher = replyPublisher;
	}
	public int getReplyFloor() {
		return replyFloor;
	}
	public void setReplyFloor(int replyFloor) {
		this.replyFloor = replyFloor;
	}
	public String getReplyText() {
		return replyText;
	}
	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "NoticeReply [replyId=" + replyId + ", replyNoticeId=" + replyNoticeId + ", replyJudge=" + replyJudge
				+ ", replyDateTime=" + replyDateTime + ", replyResponder=" + replyResponder + ", replyPublisher="
				+ replyPublisher + ", replyFloor=" + replyFloor + ", replyText=" + replyText + "]";
	}
}
