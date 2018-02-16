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
 * ��������
 * @author Administrator
 *
 */
public class NoticeService {
	/**
	 * ��ȡ���й���
	 * @return
	 */
	public static List<Announcement> getAllAnnouncements(){
		return AnnouncementDao.getAllAnnouncement();
	}
	/**
	 * ��ӹ���
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
	 * ����ʱ����ȡ����
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
	 * ����ʱ������¹��������
	 * @param time
	 * @return
	 */
	public static int countNewNotice(String time){
		return AnnouncementDao.countNewNoticeByTime(time);
	}
	/**
	 * ɾ����������
	 * @param id
	 */
	public static void deleteOneAnnouncement(String id){
		AnnouncementDao.deleteOneAnnouncement(Integer.valueOf(id));
	}
	/**
	 * ����ɾ������
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
	 * ��ӹ���ظ�
	 * @param reply
	 * @return
	 */
	public static int addNoticeReply(NoticeReply reply){
		return NoticeReplyDao.addNoticeReply(reply);
	}
	/**
	 * ���ݹ���id��ȡ����ظ�
	 * @param noticeId
	 * @return
	 */
	public static List<NoticeReply> getNoticeReplys(int noticeId){
		return NoticeReplyDao.getNoticeReply(noticeId);
	}
	
	/**
	 * ���ͻ��˱��ػ����id�Ƿ���Ҫɾ��
	 * ���ͻ��˱��صĻ���id�ڷ��������Ƿ��Ѿ���ɾ��
	 * @param flag new or old  ��ȡ�����ݻ��Ǿ�����
	 * @param deleteId �ͻ��˻����id ��ʽ�� "id1,id2,id3..."
	 * @param postListSize ��ȡ�����ӵ�����
	 * @return
	 */
	public static String checkDeletedId(String flag, String deleteId, int annListSize){
		if(flag.equals("old")){  //����ǻ�ȡ�����ݣ��򲻼��id
			return "null";
		} else if(flag.equals("new")){
			if(annListSize<=10){ //���ɾ��
				String[] ids = deleteId.split(",");
				List<Integer> idList = new ArrayList<Integer>();
				for(String id : ids){
					if(!id.trim().equals("null")){
						idList.add(Integer.valueOf(id));
					}
				}
				//��� -1 ����ֹ ��������Ϊ �գ�����
				idList.add(-1);
				//����ȡ���ڵ�id
				List<Integer> idsExited = AnnouncementDao.checkExistedIds(idList);
				//��ò����ڵ�id
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
