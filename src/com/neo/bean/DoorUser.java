package com.neo.bean;

public class DoorUser {
	private int id; //id
	private String dName; //用户名
	private String dPassword; //密码
	private String dPhone; //用户手机号码
	private String Building; 
	private String DoorId;
	private String dImagePath;//头像储存路径
	private String dODPass;//开门密码
	private String friend;
	
	public String getFriend() {
		return friend;
	}
	public void setFriend(String friend) {
		this.friend = friend;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getdPassword() {
		return dPassword;
	}
	public void setdPassword(String dPassword) {
		this.dPassword = dPassword;
	}
	public String getdPhone() {
		return dPhone;
	}
	public void setdPhone(String dPhone) {
		this.dPhone = dPhone;
	}
	public String getBuilding() {
		return Building;
	}
	public void setBuilding(String building) {
		Building = building;
	}
	public String getDoorId() {
		return DoorId;
	}
	public void setDoorId(String doorId) {
		DoorId = doorId;
	}
	public String getdImagePath() {
		return dImagePath;
	}
	public void setdImagePath(String dImagePath) {
		this.dImagePath = dImagePath;
	}
	public String getdODPass() {
		return dODPass;
	}
	public void setdODPass(String dODPass) {
		this.dODPass = dODPass;
	}
	@Override
	public String toString() {
		return "DoorUser [id=" + id + ", dName=" + dName + ", dPassword=" + dPassword + ", dPhone=" + dPhone
				+ ", Building=" + Building + ", DoorId=" + DoorId + ", dImagePath=" + dImagePath + ", dODPass="
				+ dODPass + "]";
	}
	
	
	
}
