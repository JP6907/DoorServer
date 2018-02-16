package com.neo.service;

import java.util.ArrayList;
import java.util.List;

import com.neo.bean.DoorUser;
import com.neo.bean.FriendInfo;
import com.neo.bean.Friends;
import com.neo.dao.DoorUserDao;
import com.neo.dao.FriendsDao;
import com.neo.dao.ManagerDao;

/**
 * 用户服务层
 * @author Administrator
 *
 */
public class UserService {
	/**
	 * 用户登录
	 * @param userName
	 * @param passWord
	 * @return 用户信息
	 */
	public static DoorUser doorUserLogin(String phone, String passWord){
		return DoorUserDao.login(phone, passWord);
	}
	/**
	 * 用户注册
	 * @param userName
	 * @param passWord
	 * @return 注册后的id ，已存在或出错则返回-1
	 */
	public static boolean doorUserRegister(String phone, String passWord){
		boolean isExist = DoorUserDao.isUserExist(phone);
		int id;
		if(isExist){ //如果用户已经存在
			id = -1;
		} else{
			id = DoorUserDao.register(phone, passWord);
		}
		if(id!=-1){
			return true;
		} else{
			return false;
		}
	}
	/**
	 * 更新用户信息
	 * @param updateField
	 * @param updateContent
	 * @param ID
	 * @return
	 */
	public static int updateInformation(String updateField, String updateContent, int ID){
		return DoorUserDao.update(updateField, updateContent, ID);
	}
	/**
	 * 保存用户头像地址
	 * @param phone
	 * @param path
	 * @return
	 */
	public static boolean savePic(String phone, String path){
		return DoorUserDao.savePic(phone, path);
	}
	/**
	 * 获取头像地址
	 * @param phone
	 * @return
	 */
	public static String getPicPath(String phone){
		return DoorUserDao.getPicPath(phone);
	}
	/**
	 * 验证开门密码
	 * @param phone
	 * @param password
	 * @return
	 */
	public static boolean checkDoorPassword(String phone, String password){
		return DoorUserDao.checkDoorPassword(phone, password);
	}
	/**
	 * 管理员登陆
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public static boolean managerLogin(String userName, String passWord){
		return ManagerDao.managerLogin(userName, passWord);
	}
	/**
	 * 添加好友
	 * @param id1
	 * @param id2
	 */
	public static void addFriend(String id1, String id2){
		int id1Int = Integer.valueOf(id1);
		int id2Int = Integer.valueOf(id2);
		//互相添加到对方的好友列表中
		DoorUserDao.addFriend(id1Int, id2Int);
		DoorUserDao.addFriend(id2Int, id1Int);
	}
	/**
	 * 获取好友列表
	 * @param id
	 * @return 格式: id1,id2,id3,...
	 */
	public static String getFriends(String id){
		return DoorUserDao.getFriendsId(Integer.valueOf(id));
	}
	/**
	 * 判断用户是否为好友
	 * @param id1
	 * @param id2
	 * @return
	 */
	public static boolean isFriends(String id1, String id2){
		String friendsId = DoorUserDao.getFriendsId(Integer.valueOf(id1));
		String[] ids = friendsId.split(",");
		if(ids==null || ids.length==0){
			return false;
		}
		for(String id: ids){
			if(id!=null&&!id.trim().equals("")){
				if(id.trim().equals(id2)){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 根据用户id查询所有好友的信息
	 * 信息内容包含： 名字，手机号码，备注名，头像地址
	 * @param userId
	 * @return
	 */
	public static List<FriendInfo> searchFriends(String userId){
		List<FriendInfo> friendList = new ArrayList<FriendInfo>();
		List<Friends> friends = new ArrayList<Friends>();  //只包含id，remarkname ， group
		friends = FriendsDao.searchFriends(Integer.valueOf(userId));
		//下面根据id从 DoorUser表中获取到 其它信息
		for(Friends friend: friends){
			FriendInfo friendInfo = new FriendInfo();
			friendInfo.setRemarkName(friend.getRemarkName());
			//从DoorUser表中获取其它信息
			int friendId = friend.getFriendId();
			DoorUser friendUser = new DoorUser();
			friendUser = DoorUserDao.searchByFriendId(friendId);
			friendInfo.setPhone(friendUser.getdPhone());
			friendInfo.setNickname(friendUser.getdName());
			friendInfo.setImgPath(friendUser.getdImagePath());
			
			friendList.add(friendInfo);
		}
		return friendList;
	}
	/**
	 * 设置新的密码
	 * @param phone
	 * @param password
	 * @param passwordType
	 */
	public static void setNewPassword(String phone, String password, String passwordType){
		if(passwordType.equals("userPassword")){
			DoorUserDao.setNewPassword(phone, password);
		} else if(passwordType.equals("doorPassword")){
			DoorUserDao.setNewDoorPassword(phone, password);
		}
	}
	/**
	 * 查询用户信息
	 * @param phone 用户手机号码
	 */
	public static DoorUser queryUser(String phone){
		return DoorUserDao.queryUser(phone);
		
	}
	
	public static void main(String[] args) {
		List<FriendInfo> friendList = new ArrayList<FriendInfo>();
		friendList = searchFriends("17");
		System.out.println(friendList.size());
		for(FriendInfo friendInfo: friendList){
			System.out.println(friendInfo);
		}
	}
}
