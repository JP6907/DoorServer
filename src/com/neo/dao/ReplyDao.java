package com.neo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mysql.jdbc.MiniAdmin;
import com.neo.bean.Reply;
import com.neo.util.DBAccess;

public class ReplyDao {
	/**
	 * 添加帖子回复
	 * @param reply
	 * @return
	 */
	public static int addReply(Reply reply){
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		int replyId; //插入后返回的ID
		int postId = reply.getReply_postid();
		try{
			sqlSession = dbAccess.getSqlSession();
			//添加回复内容到回复表中
			sqlSession.update("Reply.addReply", reply);
			sqlSession.commit();
			sqlSession.update("PostProfile.increaseReplyNum", postId);
			sqlSession.commit();
			//获取返回的id
			replyId = reply.getReply_id();
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
	 * @param postId 公告的id
	 * @return 回复列表
	 */
	public static List<Reply> getReply(int postId){
		List<Reply> replyList = new ArrayList<Reply>();
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//执行sql语句
			replyList = sqlSession.selectList("Reply.getReplys", postId);
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
		Reply reply = new Reply();
		reply.setReply_postid(2);
		reply.setReply_text("测试帖子回复内容");
		int id = addReply(reply);
		System.out.println(id);
	}
}
