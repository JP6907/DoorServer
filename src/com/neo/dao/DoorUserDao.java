package com.neo.dao;

import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.neo.bean.DoorUser;
import com.neo.util.DBAccess;

/**
 * 用户类相关的数据库操作类
 * 
 * @author Administrator
 *
 */
public class DoorUserDao {
	public final static int UserExit = -1;
	public final static int Error = -2;

	/**
	 * 用户登录
	 * 
	 * @param phone
	 * @param passWord
	 * @return 用户类 包含用户的各项信息
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
			// 执行sql语句
			userList = sqlSession.selectList("DoorUser.login", user);
		} catch (Exception e) {
			System.out.println(e.toString());
			// 关闭会话对象
			sqlSession.close();
		} finally {
			// 关闭会话对象
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
	 * 查询用户是否存在
	 * @param phone 用户手机号码
	 * @return
	 */
	public static boolean isUserExist(String phone) {
		List<DoorUser> userList = new ArrayList<DoorUser>();
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			// 执行sql语句
			userList = sqlSession.selectList("DoorUser.findUserByPhone", phone);
		} catch (Exception e) {
			System.out.println(e.toString());
			// 关闭会话对象
			sqlSession.close();
		} finally {
			// 关闭会话对象
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
	 * 注册
	 * 
	 * @param phone
	 * @param passWord
	 * @return 注册后的is
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
			// 获取插入后的id
			id = user.getId();
		} catch (Exception e) {
			System.out.println(e.toString());
			// 关闭会话对象
			sqlSession.close();
			id = -1;
		} finally {
			// 关闭会话对象
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return id;
	}

	/**
	 * 更新用户信息
	 * 
	 * @param updateField
	 *            更新的属性
	 * @param updateContent
	 *            更新的内容
	 * @param ID
	 *            用户id
	 * @return 更新结果
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
			// 关闭会话对象
			sqlSession.close();
			result = -1;
		} finally {
			// 关闭会话对象
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return result;
	}

	/**
	 * 保存用户头像地址
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
			// 关闭会话对象
			sqlSession.close();
			result = false;
		} finally {
			// 关闭会话对象
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return result;
	}

	/**
	 * 获取头像保存地址
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
			// 执行sql语句
			path = sqlSession.selectOne("DoorUser.getPicPathByPhone", phone);
		} catch (Exception e) {
			System.out.println(e.toString());
			// 关闭会话对象
			sqlSession.close();
		} finally {
			// 关闭会话对象
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return path;
	}
	/**
	 * 检查开门密码
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
			// 执行sql语句
			userList = sqlSession.selectList("DoorUser.checkDoorPassWord", user);
		} catch (Exception e) {
			System.out.println(e.toString());
			// 关闭会话对象
			sqlSession.close();
		} finally {
			// 关闭会话对象
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
	 * 获取所有好友的id
	 * 格式: id1,id2,id3,...
	 * @param id
	 * @return
	 */
	public static String getFriendsId(int id) {
		String friendsId = "";
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			// 执行sql语句
			friendsId = sqlSession.selectOne("DoorUser.getFirends", id);
		} catch (Exception e) {
			System.out.println(e.toString());
			// 关闭会话对象
			sqlSession.close();
		} finally {
			// 关闭会话对象
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return friendsId.trim();
	}
	/**
	 * 添加好友
	 * @param mainId
	 * @param firendId
	 * @return
	 */
	public static void addFriend(int mainId, int newFirendId) {
		//获取好友id
		String friendsId = getFriendsId(mainId);
		if(friendsId.trim().equals("")){ //好友列表为空
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
			// 执行sql语句
			sqlSession.update("DoorUser.setFirends", doorUser);
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println(e.toString());
			// 关闭会话对象
			sqlSession.close();
		} finally {
			// 关闭会话对象
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	/**
	 * 查询好友的 名字，手机号码，头像地址的具体信息
	 * @param id
	 * @return
	 */
	public static DoorUser searchByFriendId(int id) {
		DoorUser user = new DoorUser();
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			// 执行sql语句
			user = sqlSession.selectOne("DoorUser.searchByFriendId", id);
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println(e.toString());
			// 关闭会话对象
			sqlSession.close();
		} finally {
			// 关闭会话对象
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return user;
	}
	/**
	 * 设置新登陆密码
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
			// 执行sql语句
			sqlSession.update("DoorUser.setNewPasssword", user);
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println(e.toString());
			// 关闭会话对象
			sqlSession.close();
		} finally {
			// 关闭会话对象
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	/**
	 * 设置新开门密码
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
			// 执行sql语句
			sqlSession.update("DoorUser.setNewDoorPasssword", user);
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println(e.toString());
			// 关闭会话对象
			sqlSession.close();
		} finally {
			// 关闭会话对象
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	/**
	 * 查询用户信息
	 * @param phone 用户手机号码
	 * @return
	 */
	public static DoorUser queryUser(String phone){
		DoorUser user = new DoorUser();
		user.setdPhone(phone);
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			// 执行sql语句
			user = sqlSession.selectOne("DoorUser.findUserByPhone", phone);
		} catch (Exception e) {
			System.out.println(e.toString());
			// 关闭会话对象
			sqlSession.close();
		} finally {
			// 关闭会话对象
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
