package com.neo.bean;
/**
 * ��̳���ӻظ���
 * @author Administrator
 *
 */
public class Reply {
	private int reply_id; // �ظ�ID(���������������¼ʱ������ָ����ֵ)
	private int reply_postid; // �ظ���һ������
	private int reply_judge; // �ظ��жϣ��ж��ǻظ�¥�����ǻظ�¥��
	private String reply_datetime; // �ظ�����
	private String reply_responder; // Ӧ����
	private String reply_publisher; // ������
	private int reply_floor; // ¥�㣬�÷��������ݻظ�ID��С�ж�
	private String reply_text; // �ظ�����,�����С 64KB
	
	public int getReply_id() {
		return reply_id;
	}
	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}
	public int getReply_postid() {
		return reply_postid;
	}
	public void setReply_postid(int reply_postid) {
		this.reply_postid = reply_postid;
	}
	public int getReply_judge() {
		return reply_judge;
	}
	public void setReply_judge(int reply_judge) {
		this.reply_judge = reply_judge;
	}
	public String getReply_datetime() {
		return reply_datetime;
	}
	public void setReply_datetime(String reply_datetime) {
		this.reply_datetime = reply_datetime;
	}
	public String getReply_responder() {
		return reply_responder;
	}
	public void setReply_responder(String reply_responder) {
		this.reply_responder = reply_responder;
	}
	public String getReply_publisher() {
		return reply_publisher;
	}
	public void setReply_publisher(String reply_publisher) {
		this.reply_publisher = reply_publisher;
	}
	public int getReply_floor() {
		return reply_floor;
	}
	public void setReply_floor(int reply_floor) {
		this.reply_floor = reply_floor;
	}
	public String getReply_text() {
		return reply_text;
	}
	public void setReply_text(String reply_text) {
		this.reply_text = reply_text;
	}
	
}
