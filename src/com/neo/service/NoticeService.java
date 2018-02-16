package com.neo.service;

import java.util.ArrayList;
import java.util.List;

import com.neo.bean.Announcement;
import com.neo.bean.NoticeReply;
import com.neo.dao.AnnouncementDao;
import com.neo.dao.NoticeReplyDao;
import com.neo.dao.PostProfileDao;
import com.neo.util.Timeutil;

/**
 * 公告服务层
 * @author Administrator
 *
 */
public class NoticeService {
	/**
	 * 获取所有公告
	 * @return
	 */
	public static List<Announcement> getAllAnnouncements(){
		return AnnouncementDao.getAllAnnouncement();
	}
	/**
	 * 添加公告
	 * @param ann
	 * @return
	 */
	public static int addAnnouncement(String title, String content){
		Announcement ann = new Announcement();
		String date = Timeutil.getCurrentTime();
		ann.setTitle(title);
		ann.setContent(content);
		ann.setDate(date);
		return AnnouncementDao.publicAnnouncement(ann);
	}
	/**
	 * 根据时间点获取公告
	 * @param time
	 * @return
	 */
	public static List<Announcement> getAnnouncements(String flag, String time){
		if(flag.equals("new")){
			return AnnouncementDao.getNewAnnouncements(time);
		} else if(flag.equals("old")){
			return AnnouncementDao.getOldAnnouncementsByTime(time);
		} else{
			return new ArrayList<Announcement>();
		}
	}
	/**
	 * 计算时间点后的新公告的数量
	 * @param time
	 * @return
	 */
	public static int countNewNotice(String time){
		return AnnouncementDao.countNewNoticeByTime(time);
	}
	/**
	 * 删除单条公告
	 * @param id
	 */
	public static void deleteOneAnnouncement(String id){
		AnnouncementDao.deleteOneAnnouncement(Integer.valueOf(id));
	}
	/**
	 * 批量删除公告
	 * @param ids
	 */
	public static void deleteBatchAnnouncement(String[] ids){
		List<Integer> idList = new ArrayList<Integer>();
		for(String id:ids){
			idList.add(Integer.valueOf(id));
		}
		AnnouncementDao.deleteBatchAnnouncement(idList);
	}
	/**
	 * 添加公告回复
	 * @param reply
	 * @return
	 */
	public static int addNoticeReply(NoticeReply reply){
		return NoticeReplyDao.addNoticeReply(reply);
	}
	/**
	 * 根据公告id获取公告回复
	 * @param noticeId
	 * @return
	 */
	public static List<NoticeReply> getNoticeReplys(int noticeId){
		return NoticeReplyDao.getNoticeReply(noticeId);
	}
	
	/**
	 * 检查客户端本地缓存的id是否需要删除
	 * 检查客户端本地的缓存id在服务器上是否已经被删除
	 * @param flag new or old  获取新数据还是旧数据
	 * @param deleteId 客户端缓存的id 格式： "id1,id2,id3..."
	 * @param postListSize 获取到帖子的数量
	 * @return
	 */
	public static String checkDeletedId(String flag, String deleteId, int annListSize){
		if(flag.equals("old")){  //如果是获取旧数据，则不检查id
			return "null";
		} else if(flag.equals("new")){
			if(annListSize<=10){ //检查删除
				String[] ids = deleteId.split(",");
				List<Integer> idList = new ArrayList<Integer>();
				for(String id : ids){
					if(!id.trim().equals("null")){
						idList.add(Integer.valueOf(id));
					}
				}
				//添加 -1 ，防止 搜索参数为 空，报错
				idList.add(-1);
				//检查获取存在的id
				List<Integer> idsExited = AnnouncementDao.checkExistedIds(idList);
				//获得不存在的id
				idsExited.add(-1);
				idList.removeAll(idsExited);
				if(idList.size()==0){
					return "null";
				} else{
					StringBuilder s = new StringBuilder();
					int i = 0;
					for(; i< idList.size()-1; i++){
						s.append(idList.get(i) + ",");
					}
					if(idList.size()>0){
						s.append(idList.get(i));
					}
					return s.toString();
				}
			} else{
				return "-1";
			}
		} else{
			return "null";
		}
	}
	
	public static void main(String[] args) {
		String id = checkDeletedId("new", "1,2,3,50,51", 8);
		System.out.println(id);
	}
}
