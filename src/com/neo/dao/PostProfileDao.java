package com.neo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.neo.bean.Announcement;
import com.neo.bean.PostProfile;
import com.neo.util.DBAccess;

/**
 * ����������������ݿ������
 * @author Administrator
 *
 */
public class PostProfileDao {
	/**
	 * �������
	 * @param postProfile ����ʵ����
	 * @return ����������id
	 */
	public static int addPostProfile(PostProfile postProfile){
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		int postId; //����󷵻ص�ID
		try{
			sqlSession = dbAccess.getSqlSession();
			//ִ��sql���
			sqlSession.update("PostProfile.addPostProfile", postProfile);
			sqlSession.commit();
			//��ȡ���ص�id
			postId = postProfile.getPost_id();
		}catch(Exception e){
			System.out.println(e.toString());
			//�رջỰ����
			sqlSession.close();
			postId = -1;
		}finally{
			//�رջỰ����
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
		return postId;
	}
	/**
	 * ����ʱ����������ӵ�����
	 * @param time
	 * @return
	 */
	public static int countNewPostProfilesByTime(String time){
		int count = 0;
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//ִ��sql���
			count = sqlSession.selectOne("PostProfile.countNewPostprofile", time);
		}catch(Exception e){
			System.out.println(e.toString());
			//�رջỰ����
			sqlSession.close();
		}finally{
			//�رջỰ����
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
		return count;
	}
	/**
	 * ��ȡ����    10  ������
	 * @return �����б�List
	 */
	public static List<PostProfile> getNewPostProfilesByTime(String time){
		List<PostProfile> postList = new ArrayList<PostProfile>();
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//ִ��sql���
			postList = sqlSession.selectList("PostProfile.getNewPostProfilesByTime", time);
		}catch(Exception e){
			System.out.println(e.toString());
			//�رջỰ����
			sqlSession.close();
		}finally{
			//�رջỰ����
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
		return postList;
	}
	/**
	 * ��ȡ ʱ������   �ɵ�   10��     ��������
	 * @param time ʱ���
	 * @return �����б�List
	 */
	public static List<PostProfile> getOldPostProfilesByTime(String time){
		List<PostProfile> postList = new ArrayList<PostProfile>();
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//ִ��sql���
			postList = sqlSession.selectList("PostProfile.getOldPostProfilesByTime", time);
		}catch(Exception e){
			System.out.println(e.toString());
			//�رջỰ����
			sqlSession.close();
		}finally{
			//�رջỰ����
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
		return postList;
	}
	/**
	 * ���id�Ƿ����
	 * @param ids ��ʽ�� "id1,id2,id3..."
	 * @return ���ڵ�id�б�List
	 */
	public static List<Integer> checkExistedIds(List<Integer> ids){
		List<Integer> idList = new ArrayList<Integer>();
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//ִ��sql���
			idList = sqlSession.selectList("PostProfile.getExistedId", ids);
		}catch(Exception e){
			System.out.println(e.toString());
			//�رջỰ����
			sqlSession.close();
		}finally{
			//�رջỰ����
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
		return idList;
	}
	public static void main(String[] args) {
		System.out.println(countNewPostProfilesByTime("2016-10-08 22:41:01"));
	}
}
