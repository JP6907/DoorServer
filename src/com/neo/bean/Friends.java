package com.neo.bean;
/**
 * 好友表的实体类
 * @author Administrator
 *
 */
public class Friends {
	private int id;
	private int userId; //用户id
	private int friendId; //好友的id
	private String remarkName; //备注名
	private String group;  //好友所在的分组名
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
