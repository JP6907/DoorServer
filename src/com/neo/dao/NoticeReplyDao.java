package com.neo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.neo.bean.Announcement;
import com.neo.bean.NoticeReply;
import com.neo.util.DBAccess;
import com.neo.util.Timeutil;

/**
 * 与公告回复类关联的数据库操作类
 * @author Administrator
 *
 */
public class NoticeReplyDao {
	/**
	 * 添加公告回复
	 * @param noticeReply 公告回复类
	 * @return 插入后的id
	 */
	public static int addNoticeReply(NoticeReply noticeReply){
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		int replyId; //插入后返回的ID
		try{
			sqlSession = dbAccess.getSqlSession();
			//执行sql语句
			sqlSession.update("NoticeReply.addNoticeReply", noticeReply);
			sqlSession.commit();
			//获取返回的id
			replyId = noticeReply.getReplyId();
		}catch(Exception e){
			System.out.println(e.toString());
			//关闭会话对象
			sqlSession.close();
			replyId = -1;
		}finally{
			//关闭会话对象
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
		return replyId;
	}
	/**
	 * 获取公告回复
	 * @param noticeId 公告的id
	 * @return 公告回复列表
	 */
	public static List<NoticeReply> getNoticeReply(int noticeId){
		List<NoticeReply> replyList = new ArrayList<NoticeReply>();
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//执行sql语句
			replyList = sqlSession.selectList("NoticeReply.getNoticeReplys", noticeId);
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
		return replyList;
	}
	
	public static void main(String[] args) {
		List<NoticeReply> replyList = getNoticeReply(7);
		System.out.println(replyList.size());
		for(NoticeReply nr: replyList){
			System.out.println(nr);
		}
	}
}
