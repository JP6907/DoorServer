package com.neo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.neo.bean.Announcement;
import com.neo.util.DBAccess;

/**
 * 与公告类关联的数据库操作类
 * @author neo
 *
 */
public class AnnouncementDao {
	/**
	 * 发布公告
	 * @param title 公告标题
	 * @param content 公告内容
	 * @return 返回插入后的id，错误则返回-1
	 */
	public static int publicAnnouncement(Announcement ann){
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		int annId; //插入后返回的ID
		try{
			sqlSession = dbAccess.getSqlSession();
			//执行sql语句
			sqlSession.update("Announcement.addAnnouncement", ann);
			sqlSession.commit();
			//获取返回的id
			annId = ann.getId();
		}catch(Exception e){
			System.out.println(e.toString());
			//关闭会话对象
			sqlSession.close();
			annId = -1;
		}finally{
			//关闭会话对象
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
		return annId;
	}
	/**
	 * 计算时间点后的新公告的数量
	 * @param time
	 * @return
	 */
	public static int countNewNoticeByTime(String time){
		int count = 0;
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//执行sql语句
			count = sqlSession.selectOne("Announcement.countNewPostprofile", time);
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
	 * 获取所有公告
	 * @return List<Announcement>
	 */
	public static List<Announcement> getAllAnnouncement(){
		List<Announcement> annList = new ArrayList<Announcement>();
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//执行sql语句
			annList = sqlSession.selectList("Announcement.getAnnouncementList");
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
		return annList;
	}
	/**
	 * 获取最新10条数据
	 * @return
	 */
	public static List<Announcement> getNewAnnouncements(String time){
		List<Announcement> annList = new ArrayList<Announcement>();
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//执行sql语句
			annList = sqlSession.selectList("Announcement.getNewAnnouncements", time);
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
		return annList;
	}
	/**
	 * 获取时间点前的10条数据
	 * @param time
	 * @return
	 */
	public static List<Announcement> getOldAnnouncementsByTime(String time){
		List<Announcement> annList = new ArrayList<Announcement>();
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//执行sql语句
			annList = sqlSession.selectList("Announcement.getOldAnnouncementsByTime", time);
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
		return annList;
	}
	/**
	 * 删除一条公告
	 * @param id
	 */
	public static void deleteOneAnnouncement(int id){
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//执行sql语句
			sqlSession.delete("Announcement.deleteOne", id);
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
			//关闭会话对象
			sqlSession.close();
		}finally{
			//关闭会话对象
			if(sqlSession!=null){
				sqlSession.close();
			}
		} 
	}
	/**
	 * 批量删除公告
	 * @param ids
	 */
	public static void deleteBatchAnnouncement(List<Integer> ids){
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try{
			sqlSession = dbAccess.getSqlSession();
			//执行sql语句
			sqlSession.delete("Announcement.deleteBatch", ids);
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
			//关闭会话对象
			sqlSession.close();
		}finally{
			//关闭会话对象
			if(sqlSession!=null){
				sqlSession.close();
			}
		} 
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
			idList = sqlSession.selectList("Announcement.getExistedId", ids);
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
