package com.neo.bean;
/**
 * 好友具体信息
 * @author Administrator
 *
 */
public class FriendInfo {
	private String phone;
	private String remarkName;
	private String nickname;
	private String group;
	private String imgPath;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRemarkName() {
		return remarkName;
	}
	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	@Override
	public String toString() {
		return "FriendInfo [phone=" + phone + ", remarkName=" + remarkName + ", nickname=" + nickname + ", group="
				+ group + ", imgPath=" + imgPath + "]";
	}
	
}
