package com.neo.bean;

import java.io.Serializable;
/**
 * 用户类
 * @author Administrator
 *
 */
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int ID = 0;	//id
	private String username ; //用户名
	private String phone;  //用户手机号码
	private String building = ""; //用户居住搂动
	private String doorID = "";	//门id
	private String path;
	
	
	public User(){
		
	}
	
	public User(int ID, String username, String phone, 
			String building, String doorID, String path){
		super();
		this.ID = ID;
		this.username = username;
		this.phone = phone;
		this.building = building;
		this.doorID = doorID;
		this.path = path;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getDoorID() {
		return doorID;
	}

	public void setDoorID(String doorID) {
		this.doorID = doorID;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
