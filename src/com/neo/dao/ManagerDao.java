package com.neo.dao;

import java.util.List;

import com.neo.bean.Announcement;
import com.neo.service.NoticeService;

/**
 * ����Ա��ز���
 * @author Administrator
 *
 */
public class ManagerDao {
	/**
	 * ����Ա��½����
	 * @param userName �û���
	 * @param passWord ����
	 * @return �Ƿ��½�ɹ�
	 */
	public static boolean managerLogin(String userName, String passWord){
		if(userName!=null && passWord!=null){
			if(userName.equals("admin") && passWord.equals("huikaimeng")){
				return true;
			}
		}
		return false;
	}
	/**
	 * ��������
	 * @param title ����
	 * @param content ����
	 */
	public static void publicAnnouncement(String title, String content){
		//���浽���ݿ�
		NoticeService.addAnnouncement(title, content);
	}
	/**
	 * ��ȡ�����б�
	 * @return �����б�
	 */
	public static List<Announcement> getAnnouncementList(){
		return NoticeService.getAllAnnouncements();
	}
	/**
	 * ɾ��һ������
	 * @param id
	 */
	public static void deleteOneAnnouncement(String id){
		NoticeService.deleteOneAnnouncement(id);
	}
	/**
	 * ����ɾ������
	 * @param ids
	 */
	public static void deleteBatchAnnouncement(String[] ids){
		if(ids!=null && ids.length>0){
			NoticeService.deleteBatchAnnouncement(ids);
		}
	}
}
