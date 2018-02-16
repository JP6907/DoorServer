package com.neo.bean;

import java.io.Serializable;
/**
 * ����ظ���
 * @author Administrator
 *
 */
public class NoticeReply implements Serializable{
	private static final long serialVersionUID = 8257429880277711117L;
	//id
	private int replyId;
	//�ظ��Ĺ����id
	private int replyNoticeId;
	//�ظ��жϣ��ж��Ƿ�ظ�§�������ǻظ��ظ�
	private Integer replyJudge; 
	//�ظ�����
	private String replyDateTime;
	//�ظ���
	private String replyResponder; 
	//
	private String replyPublisher; 
	//�ظ���¥��
	private int replyFloor;
	//�ظ�����
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
