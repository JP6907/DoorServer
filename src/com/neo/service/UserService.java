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
 * �û������
 * @author Administrator
 *
 */
public class UserService {
	/**
	 * �û���¼
	 * @param userName
	 * @param passWord
	 * @return �û���Ϣ
	 */
	public static DoorUser doorUserLogin(String phone, String passWord){
		return DoorUserDao.login(phone, passWord);
	}
	/**
	 * �û�ע��
	 * @param userName
	 * @param passWord
	 * @return ע����id ���Ѵ��ڻ�����򷵻�-1
	 */
	public static boolean doorUserRegister(String phone, String passWord){
		boolean isExist = DoorUserDao.isUserExist(phone);
		int id;
		if(isExist){ //����û��Ѿ�����
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
	 * �����û���Ϣ
	 * @param updateField
	 * @param updateContent
	 * @param ID
	 * @return
	 */
	public static int updateInformation(String updateField, String updateContent, int ID){
		return DoorUserDao.update(updateField, updateContent, ID);
	}
	/**
	 * �����û�ͷ���ַ
	 * @param phone
	 * @param path
	 * @return
	 */
	public static boolean savePic(String phone, String path){
		return DoorUserDao.savePic(phone, path);
	}
	/**
	 * ��ȡͷ���ַ
	 * @param phone
	 * @return
	 */
	public static String getPicPath(String phone){
		return DoorUserDao.getPicPath(phone);
	}
	/**
	 * ��֤��������
	 * @param phone
	 * @param password
	 * @return
	 */
	public static boolean checkDoorPassword(String phone, String password){
		return DoorUserDao.checkDoorPassword(phone, password);
	}
	/**
	 * ����Ա��½
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public static boolean managerLogin(String userName, String passWord){
		return ManagerDao.managerLogin(userName, passWord);
	}
	/**
	 * ��Ӻ���
	 * @param id1
	 * @param id2
	 */
	public static void addFriend(String id1, String id2){
		int id1Int = Integer.valueOf(id1);
		int id2Int = Integer.valueOf(id2);
		//������ӵ��Է��ĺ����б���
		DoorUserDao.addFriend(id1Int, id2Int);
		DoorUserDao.addFriend(id2Int, id1Int);
	}
	/**
	 * ��ȡ�����б�
	 * @param id
	 * @return ��ʽ: id1,id2,id3,...
	 */
	public static String getFriends(String id){
		return DoorUserDao.getFriendsId(Integer.valueOf(id));
	}
	/**
	 * �ж��û��Ƿ�Ϊ����
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
	 * �����û�id��ѯ���к��ѵ���Ϣ
	 * ��Ϣ���ݰ����� ���֣��ֻ����룬��ע����ͷ���ַ
	 * @param userId
	 * @return
	 */
	public static List<FriendInfo> searchFriends(String userId){
		List<FriendInfo> friendList = new ArrayList<FriendInfo>();
		List<Friends> friends = new ArrayList<Friends>();  //ֻ����id��remarkname �� group
		friends = FriendsDao.searchFriends(Integer.valueOf(userId));
		//�������id�� DoorUser���л�ȡ�� ������Ϣ
		for(Friends friend: friends){
			FriendInfo friendInfo = new FriendInfo();
			friendInfo.setRemarkName(friend.getRemarkName());
			//��DoorUser���л�ȡ������Ϣ
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
	 * �����µ�����
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
	 * ��ѯ�û���Ϣ
	 * @param phone �û��ֻ�����
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
