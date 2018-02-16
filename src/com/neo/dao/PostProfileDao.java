package com.neo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.neo.bean.Announcement;
import com.neo.bean.PostProfile;
import com.neo.util.DBAccess;

/**
 * 与帖子类关联的数据库操作类
 * @author Administrator
 *
 */
public class PostProfileDao {
	/**
	 * 添加帖子
	 * @param postProfile 帖子实体类
	 * @return 插入后的帖子id
	 */
	public static int addPostProfile(PostProfile postProfile){
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		int postId; //插入后返回的ID
		try{
			sqlSession = dbAccess.getSqlSession();
			//执行sql语句
			sqlSession.update("PostProfile.addPostProfile", postProfile);
			sqlSession.commit();
			//获取返回的id
			postId = postProfile.getPost_id();
		}catch(Exception e){
			System.out.println(e.toString());
			//关闭会话对象
			sqlSession.close();
			postId = -1;
		}finally{
			//关闭会话对象
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
		return postId;
	}
	/**
	 * 计算时间点后的新帖子的数量
	 * @param time
	 * @return
	 */
	public static int countNewPostProfilesByTime(String time){
		int count = 0;
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//执行sql语句
			count = sqlSession.selectOne("PostProfile.countNewPostprofile", time);
		}catch(Exception e){
			System.out.println(e.toString());
			//关闭会话对象
			sqlSession.close();
		}finally{
			//关闭会话对象
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
		return count;
	}
	/**
	 * 获取最新    10  条数据
	 * @return 帖子列表List
	 */
	public static List<PostProfile> getNewPostProfilesByTime(String time){
		List<PostProfile> postList = new ArrayList<PostProfile>();
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//执行sql语句
			postList = sqlSession.selectList("PostProfile.getNewPostProfilesByTime", time);
		}catch(Exception e){
			System.out.println(e.toString());
			//关闭会话对象
			sqlSession.close();
		}finally{
			//关闭会话对象
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
		return postList;
	}
	/**
	 * 获取 时间点后面   旧的   10条     帖子数据
	 * @param time 时间点
	 * @return 帖子列表List
	 */
	public static List<PostProfile> getOldPostProfilesByTime(String time){
		List<PostProfile> postList = new ArrayList<PostProfile>();
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//执行sql语句
			postList = sqlSession.selectList("PostProfile.getOldPostProfilesByTime", time);
		}catch(Exception e){
			System.out.println(e.toString());
			//关闭会话对象
			sqlSession.close();
		}finally{
			//关闭会话对象
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
		return postList;
	}
	/**
	 * 检查id是否存在
	 * @param ids 格式： "id1,id2,id3..."
	 * @return 存在的id列表List
	 */
	public static List<Integer> checkExistedIds(List<Integer> ids){
		List<Integer> idList = new ArrayList<Integer>();
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//执行sql语句
			idList = sqlSession.selectList("PostProfile.getExistedId", ids);
		}catch(Exception e){
			System.out.println(e.toString());
			//关闭会话对象
			sqlSession.close();
		}finally{
			//关闭会话对象
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
