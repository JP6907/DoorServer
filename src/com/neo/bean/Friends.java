package com.neo.bean;
/**
 * ���ѱ��ʵ����
 * @author Administrator
 *
 */
public class Friends {
	private int id;
	private int userId; //�û�id
	private int friendId; //���ѵ�id
	private String remarkName; //��ע��
	private String group;  //�������ڵķ�����
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public String getRemarkName() {
		return remarkName;
	}
	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
	
}
