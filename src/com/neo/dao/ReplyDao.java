package com.neo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mysql.jdbc.MiniAdmin;
import com.neo.bean.Reply;
import com.neo.util.DBAccess;

public class ReplyDao {
	/**
	 * ������ӻظ�
	 * @param reply
	 * @return
	 */
	public static int addReply(Reply reply){
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		int replyId; //����󷵻ص�ID
		int postId = reply.getReply_postid();
		try{
			sqlSession = dbAccess.getSqlSession();
			//��ӻظ����ݵ��ظ�����
			sqlSession.update("Reply.addReply", reply);
			sqlSession.commit();
			sqlSession.update("PostProfile.increaseReplyNum", postId);
			sqlSession.commit();
			//��ȡ���ص�id
			replyId = reply.getReply_id();
		}catch(Exception e){
			System.out.println(e.toString());
			//�رջỰ����
			sqlSession.close();
			replyId = -1;
		}finally{
			//�رջỰ����
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
		return replyId;
	}
	/**
	 * ��ȡ����ظ�
	 * @param postId �����id
	 * @return �ظ��б�
	 */
	public static List<Reply> getReply(int postId){
		List<Reply> replyList = new ArrayList<Reply>();
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//ִ��sql���
			replyList = sqlSession.selectList("Reply.getReplys", postId);
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
		return replyList;
	}
	
	public static void main(String[] args) {
		Reply reply = new Reply();
		reply.setReply_postid(2);
		reply.setReply_text("�������ӻظ�����");
		int id = addReply(reply);
		System.out.println(id);
	}
}
