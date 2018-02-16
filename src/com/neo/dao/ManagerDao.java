package com.neo.dao;

import java.util.List;

import com.neo.bean.Announcement;
import com.neo.service.NoticeService;

/**
 * 管理员相关操作
 * @author Administrator
 *
 */
public class ManagerDao {
	/**
	 * 管理员登陆操作
	 * @param userName 用户名
	 * @param passWord 密码
	 * @return 是否登陆成功
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
	 * 发布公告
	 * @param title 标题
	 * @param content 内容
	 */
	public static void publicAnnouncement(String title, String content){
		//保存到数据库
		NoticeService.addAnnouncement(title, content);
	}
	/**
	 * 获取公告列表
	 * @return 公告列表
	 */
	public static List<Announcement> getAnnouncementList(){
		return NoticeService.getAllAnnouncements();
	}
	/**
	 * 删除一条公告
	 * @param id
	 */
	public static void deleteOneAnnouncement(String id){
		NoticeService.deleteOneAnnouncement(id);
	}
	/**
	 * 批量删除公告
	 * @param ids
	 */
	public static void deleteBatchAnnouncement(String[] ids){
		if(ids!=null && ids.length>0){
			NoticeService.deleteBatchAnnouncement(ids);
		}
	}
}
