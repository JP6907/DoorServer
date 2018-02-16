package com.neo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.neo.bean.Friends;
import com.neo.util.DBAccess;

/**
 * 与好友表数据库操作相关的操作类
 * 
 * @author Administrator
 *
 */
public class FriendsDao {
	/**
	 * 根据用户id搜索好友
	 * 
	 * @param userId
	 */
	public static List<Friends> searchFriends(int userId) {
		List<Friends> friendList = new ArrayList<Friends>();	
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			// 执行sql语句
			friendList = sqlSession.selectList("Friends.searchFriendsById", userId);
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
		return friendList;
	}
	
	public static void main(String[] args) {
		List<Friends> friendList = new ArrayList<Friends>();
		friendList = searchFriends(17);
		System.out.println(friendList.size());
		for(Friends friend:friendList){
			System.out.println(friend.getFriendId() + "  " + friend.getRemarkName() + friend.getGroup());
		}
	}
}
