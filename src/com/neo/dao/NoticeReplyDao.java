package com.neo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.neo.bean.Announcement;
import com.neo.bean.NoticeReply;
import com.neo.util.DBAccess;
import com.neo.util.Timeutil;

/**
 * �빫��ظ�����������ݿ������
 * @author Administrator
 *
 */
public class NoticeReplyDao {
	/**
	 * ��ӹ���ظ�
	 * @param noticeReply ����ظ���
	 * @return ������id
	 */
	public static int addNoticeReply(NoticeReply noticeReply){
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		int replyId; //����󷵻ص�ID
		try{
			sqlSession = dbAccess.getSqlSession();
			//ִ��sql���
			sqlSession.update("NoticeReply.addNoticeReply", noticeReply);
			sqlSession.commit();
			//��ȡ���ص�id
			replyId = noticeReply.getReplyId();
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
	 * @param noticeId �����id
	 * @return ����ظ��б�
	 */
	public static List<NoticeReply> getNoticeReply(int noticeId){
		List<NoticeReply> replyList = new ArrayList<NoticeReply>();
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//ִ��sql���
			replyList = sqlSession.selectList("NoticeReply.getNoticeReplys", noticeId);
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
		List<NoticeReply> replyList = getNoticeReply(7);
		System.out.println(replyList.size());
		for(NoticeReply nr: replyList){
			System.out.println(nr);
		}
	}
}
