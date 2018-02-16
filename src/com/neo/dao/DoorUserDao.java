package com.neo.dao;

import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.neo.bean.DoorUser;
import com.neo.util.DBAccess;

/**
 * �û�����ص����ݿ������
 * 
 * @author Administrator
 *
 */
public class DoorUserDao {
	public final static int UserExit = -1;
	public final static int Error = -2;

	/**
	 * �û���¼
	 * 
	 * @param phone
	 * @param passWord
	 * @return �û��� �����û��ĸ�����Ϣ
	 */
	public static DoorUser login(String phone, String passWord) {
		DoorUser user = new DoorUser();
		user.setdPhone(phone);
		user.setdPassword(passWord);
		List<DoorUser> userList = new ArrayList<DoorUser>();
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			// ִ��sql���
			userList = sqlSession.selectList("DoorUser.login", user);
		} catch (Exception e) {
			System.out.println(e.toString());
			// �رջỰ����
			sqlSession.close();
		} finally {
			// �رջỰ����
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		if (userList != null && userList.size() != 0) {
			return userList.get(0);
		} else {
			return null;
		}
	}
	/**
	 * ��ѯ�û��Ƿ����
	 * @param phone �û��ֻ�����
	 * @return
	 */
	public static boolean isUserExist(String phone) {
		List<DoorUser> userList = new ArrayList<DoorUser>();
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			// ִ��sql���
			userList = sqlSession.selectList("DoorUser.findUserByPhone", phone);
		} catch (Exception e) {
			System.out.println(e.toString());
			// �رջỰ����
			sqlSession.close();
		} finally {
			// �رջỰ����
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		if (userList != null && userList.size() != 0) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * ע��
	 * 
	 * @param phone
	 * @param passWord
	 * @return ע����is
	 */
	public static int register(String phone, String passWord) {
		DoorUser user = new DoorUser();
		user.setdPhone(phone);
		user.setdPassword(passWord);
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		int id;
		try {
			sqlSession = dbAccess.getSqlSession();
			sqlSession.update("DoorUser.register", user);
			sqlSession.commit();
			// ��ȡ������id
			id = user.getId();
		} catch (Exception e) {
			System.out.println(e.toString());
			// �رջỰ����
			sqlSession.close();
			id = -1;
		} finally {
			// �رջỰ����
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return id;
	}

	/**
	 * �����û���Ϣ
	 * 
	 * @param updateField
	 *            ���µ�����
	 * @param updateContent
	 *            ���µ�����
	 * @param ID
	 *            �û�id
	 * @return ���½��
	 */
	public static int update(String updateField, String updateContent, int ID) {
		int result = -1;
		DoorUser user = new DoorUser();
		user.setId(ID);
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			switch (updateField) {
			case "Name":
				user.setdName(updateContent);
				sqlSession.update("DoorUser.updateName", user);
				sqlSession.commit();
				result = 1;
				break;
			case "Password":
				
				String[] password = updateContent.split("-");
				user.setdPassword(password[0]);
				List<DoorUser> list1 = new ArrayList<DoorUser>();
				list1 = sqlSession.selectList("DoorUser.checkPassWordById", user);
				if(list1.size()==0){
					result = 7; 
				} else{
					user.setdPassword(password[1]);
					sqlSession.update("DoorUser.updatePassword", user);
					sqlSession.commit();
					result = 2;
				}
				break;
				
			case "Phone":
				user.setdPhone(updateContent);
				sqlSession.update("DoorUser.updatePhone", user);
				sqlSession.commit();
				result = 3;
				break;
			case "Building":
				user.setBuilding(updateContent);
				sqlSession.update("DoorUser.updateBuilding", user);
				sqlSession.commit();
				result = 4;
				break;
			case "DoorID":
				user.setDoorId(updateContent);
				sqlSession.update("DoorUser.updateDoorID", user);
				sqlSession.commit();
				result = 5;
				break;
			case "dODPass":
				String[] doorPassword = updateContent.split("-");
				user.setdODPass(doorPassword[0]);
				List<DoorUser> list2 = new ArrayList<DoorUser>();
				list2 = sqlSession.selectList("DoorUser.checkDoorPassWordById", user);
				if(list2.size()==0){
					result = 7; 
				} else{
					user.setdODPass(doorPassword[1]);
					sqlSession.update("DoorUser.updateDoorPassword", user);
					sqlSession.commit();
					result = 6;
				}
				break;
			default:
				break;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			// �رջỰ����
			sqlSession.close();
			result = -1;
		} finally {
			// �رջỰ����
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return result;
	}

	/**
	 * �����û�ͷ���ַ
	 * 
	 * @param name
	 * @param path
	 * @return
	 */
	public static boolean savePic(String phone, String path) {
		DoorUser user = new DoorUser();
		user.setdPhone(phone);
		user.setdImagePath(path);
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		boolean result;
		try {
			sqlSession = dbAccess.getSqlSession();
			sqlSession.update("DoorUser.updatePicPath", user);
			sqlSession.commit();
			result = true;
		} catch (Exception e) {
			System.out.println(e.toString());
			// �رջỰ����
			sqlSession.close();
			result = false;
		} finally {
			// �رջỰ����
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return result;
	}

	/**
	 * ��ȡͷ�񱣴��ַ
	 * 
	 * @param name
	 * @return
	 */
	public static String getPicPath(String phone) {
		String path = null;
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			// ִ��sql���
			path = sqlSession.selectOne("DoorUser.getPicPathByPhone", phone);
		} catch (Exception e) {
			System.out.println(e.toString());
			// �رջỰ����
			sqlSession.close();
		} finally {
			// �رջỰ����
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return path;
	}
	/**
	 * ��鿪������
	 * @param phone
	 * @param password
	 * @return
	 */
	public static boolean checkDoorPassword(String phone, String password){
		DoorUser user = new DoorUser();
		user.setdPhone(phone);
		user.setdODPass(password);
		List<DoorUser> userList = new ArrayList<DoorUser>();
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			// ִ��sql���
			userList = sqlSession.selectList("DoorUser.checkDoorPassWord", user);
		} catch (Exception e) {
			System.out.println(e.toString());
			// �رջỰ����
			sqlSession.close();
		} finally {
			// �رջỰ����
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		if(userList!=null && userList.size()!=0){
			return true;
		} else{
			return false;
		}
	}
	/**
	 * ��ȡ���к��ѵ�id
	 * ��ʽ: id1,id2,id3,...
	 * @param id
	 * @return
	 */
	public static String getFriendsId(int id) {
		String friendsId = "";
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			// ִ��sql���
			friendsId = sqlSession.selectOne("DoorUser.getFirends", id);
		} catch (Exception e) {
			System.out.println(e.toString());
			// �رջỰ����
			sqlSession.close();
		} finally {
			// �رջỰ����
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return friendsId.trim();
	}
	/**
	 * ��Ӻ���
	 * @param mainId
	 * @param firendId
	 * @return
	 */
	public static void addFriend(int mainId, int newFirendId) {
		//��ȡ����id
		String friendsId = getFriendsId(mainId);
		if(friendsId.trim().equals("")){ //�����б�Ϊ��
			friendsId = newFirendId + "";
		} else{
			friendsId = friendsId + "," + newFirendId;
		}
		DoorUser doorUser = new DoorUser();
		doorUser.setId(Integer.valueOf(mainId));
		doorUser.setFriend(friendsId);
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			// ִ��sql���
			sqlSession.update("DoorUser.setFirends", doorUser);
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println(e.toString());
			// �رջỰ����
			sqlSession.close();
		} finally {
			// �رջỰ����
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	/**
	 * ��ѯ���ѵ� ���֣��ֻ����룬ͷ���ַ�ľ�����Ϣ
	 * @param id
	 * @return
	 */
	public static DoorUser searchByFriendId(int id) {
		DoorUser user = new DoorUser();
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			// ִ��sql���
			user = sqlSession.selectOne("DoorUser.searchByFriendId", id);
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println(e.toString());
			// �رջỰ����
			sqlSession.close();
		} finally {
			// �رջỰ����
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return user;
	}
	/**
	 * �����µ�½����
	 * @param phone
	 * @param password
	 * @return
	 */
	public static void setNewPassword(String phone, String password) {
		DoorUser user = new DoorUser();
		user.setdPhone(phone);
		user.setdPassword(password);
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			// ִ��sql���
			sqlSession.update("DoorUser.setNewPasssword", user);
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println(e.toString());
			// �رջỰ����
			sqlSession.close();
		} finally {
			// �رջỰ����
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	/**
	 * �����¿�������
	 * @param phone
	 * @param password
	 */
	public static void setNewDoorPassword(String phone, String password) {
		DoorUser user = new DoorUser();
		user.setdPhone(phone);
		user.setdODPass(password);
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			// ִ��sql���
			sqlSession.update("DoorUser.setNewDoorPasssword", user);
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println(e.toString());
			// �رջỰ����
			sqlSession.close();
		} finally {
			// �رջỰ����
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	/**
	 * ��ѯ�û���Ϣ
	 * @param phone �û��ֻ�����
	 * @return
	 */
	public static DoorUser queryUser(String phone){
		DoorUser user = new DoorUser();
		user.setdPhone(phone);
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			// ִ��sql���
			user = sqlSession.selectOne("DoorUser.findUserByPhone", phone);
		} catch (Exception e) {
			System.out.println(e.toString());
			// �رջỰ����
			sqlSession.close();
		} finally {
			// �رջỰ����
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return user;
	}
	public static void main(String[] args) {
		setNewDoorPassword("15521374237", "888888");
	}
}
