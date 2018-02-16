package com.neo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.neo.bean.Friends;
import com.neo.util.DBAccess;

/**
 * ����ѱ����ݿ������صĲ�����
 * 
 * @author Administrator
 *
 */
public class FriendsDao {
	/**
	 * �����û�id��������
	 * 
	 * @param userId
	 */
	public static List<Friends> searchFriends(int userId) {
		List<Friends> friendList = new ArrayList<Friends>();	
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			// ִ��sql���
			friendList = sqlSession.selectList("Friends.searchFriendsById", userId);
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
