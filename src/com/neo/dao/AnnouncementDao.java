package com.neo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.neo.bean.Announcement;
import com.neo.util.DBAccess;

/**
 * �빫������������ݿ������
 * @author neo
 *
 */
public class AnnouncementDao {
	/**
	 * ��������
	 * @param title �������
	 * @param content ��������
	 * @return ���ز�����id�������򷵻�-1
	 */
	public static int publicAnnouncement(Announcement ann){
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		int annId; //����󷵻ص�ID
		try{
			sqlSession = dbAccess.getSqlSession();
			//ִ��sql���
			sqlSession.update("Announcement.addAnnouncement", ann);
			sqlSession.commit();
			//��ȡ���ص�id
			annId = ann.getId();
		}catch(Exception e){
			System.out.println(e.toString());
			//�رջỰ����
			sqlSession.close();
			annId = -1;
		}finally{
			//�رջỰ����
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
		return annId;
	}
	/**
	 * ����ʱ������¹��������
	 * @param time
	 * @return
	 */
	public static int countNewNoticeByTime(String time){
		int count = 0;
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//ִ��sql���
			count = sqlSession.selectOne("Announcement.countNewPostprofile", time);
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
	 * ��ȡ���й���
	 * @return List<Announcement>
	 */
	public static List<Announcement> getAllAnnouncement(){
		List<Announcement> annList = new ArrayList<Announcement>();
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//ִ��sql���
			annList = sqlSession.selectList("Announcement.getAnnouncementList");
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
		return annList;
	}
	/**
	 * ��ȡ����10������
	 * @return
	 */
	public static List<Announcement> getNewAnnouncements(String time){
		List<Announcement> annList = new ArrayList<Announcement>();
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//ִ��sql���
			annList = sqlSession.selectList("Announcement.getNewAnnouncements", time);
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
		return annList;
	}
	/**
	 * ��ȡʱ���ǰ��10������
	 * @param time
	 * @return
	 */
	public static List<Announcement> getOldAnnouncementsByTime(String time){
		List<Announcement> annList = new ArrayList<Announcement>();
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//ִ��sql���
			annList = sqlSession.selectList("Announcement.getOldAnnouncementsByTime", time);
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
		return annList;
	}
	/**
	 * ɾ��һ������
	 * @param id
	 */
	public static void deleteOneAnnouncement(int id){
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//ִ��sql���
			sqlSession.delete("Announcement.deleteOne", id);
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
			//�رջỰ����
			sqlSession.close();
		}finally{
			//�رջỰ����
			if(sqlSession!=null){
				sqlSession.close();
			}
		} 
	}
	/**
	 * ����ɾ������
	 * @param ids
	 */
	public static void deleteBatchAnnouncement(List<Integer> ids){
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//ִ��sql���
			sqlSession.delete("Announcement.deleteBatch", ids);
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
			//�رջỰ����
			sqlSession.close();
		}finally{
			//�رջỰ����
			if(sqlSession!=null){
				sqlSession.close();
			}
		} 
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
			idList = sqlSession.selectList("Announcement.getExistedId", ids);
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
	
	public static void main(String[] args){
		List<Integer> l = new ArrayList<Integer>();
		l.add(1);
		l.add(2);
		l.add(50);
		l.add(41);
		List<Integer> idList = checkExistedIds(l);
		for(int i: idList){
			System.out.println(i);
		}
	}
}
