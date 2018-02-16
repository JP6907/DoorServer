package com.neo.service;

import java.util.ArrayList;
import java.util.List;

import com.neo.bean.PostProfile;
import com.neo.bean.Reply;
import com.neo.dao.PostProfileDao;
import com.neo.dao.ReplyDao;
import com.neo.util.Timeutil;

/**
 * 论坛服务层
 * @author Administrator
 *
 */
public class BBSService {
	/**
	 * 根据时间点获取帖子
	 * @param flag 判断获取新数据还是旧数据
	 * @param time 时间点
	 * @return
	 */
	public static List<PostProfile> GetPostProfiles(String flag, String time){
		if(flag.equals("new")){
			return PostProfileDao.getNewPostProfilesByTime(time);
		} else if(flag.equals("old")){
			return PostProfileDao.getOldPostProfilesByTime(time);
		} else{
			return new ArrayList<PostProfile>();
		}
	}
	/**
	 * 计算时间点后的新帖子的数量
	 * @param time
	 * @return
	 */
	public static int countNewPostprofile(String time){
		return PostProfileDao.countNewPostProfilesByTime(time);
	}
	/**
	 * 添加帖子
	 * @param postProfile
	 * @return
	 */
	public static int addPostProfile(PostProfile postProfile){
		return PostProfileDao.addPostProfile(postProfile);
	}
	/**
	 * 根据帖子id获取帖子回复
	 * @param postId
	 * @return
	 */
	public static List<Reply> getReply(int postId){
		return ReplyDao.getReply(postId);
	}
	/**
	 * 添加回复
	 * @param reply
	 * @return
	 */
	public static int addReply(Reply reply){
		return ReplyDao.addReply(reply);
	}
	/**
	 * 添加图片到 PostProfile 中
	 * @param postProfile
	 * @param picNames
	 */
	public static void addPic(PostProfile postProfile, List<String> picNames){
		int size = picNames.size();
		if(size==0){
			
		} if(size >=1){
			postProfile.post_picture1 = picNames.get(0);
		} if(size >=2){
			postProfile.post_picture2 = picNames.get(1);
		} if(size >=3){
			postProfile.post_picture3 = picNames.get(2);
		} if(size >=4){
			postProfile.post_picture4 = picNames.get(3);
		} if(size >=5){
			postProfile.post_picture5 = picNames.get(4);
		} if(size >=6){
			postProfile.post_picture6 = picNames.get(5);
		} if(size >=7){
			postProfile.post_picture7 = picNames.get(6);
		} if(size >=8){
			postProfile.post_picture8 = picNames.get(7);
		} if(size >=9){
			postProfile.post_picture9 = picNames.get(8);
		} 
	}
	/**
	 * 检查客户端本地缓存的id是否需要删除
	 * 检查客户端本地的缓存id在服务器上是否已经被删除
	 * @param flag new or old  获取新数据还是旧数据
	 * @param deleteId 客户端缓存的id 格式： "id1,id2,id3..."
	 * @param postListSize 获取到帖子的数量
	 * @return
	 */
	public static String checkDeletedId(String flag, String deleteId, int postListSize){
		if(flag.equals("old")){  //如果是获取旧数据，则不检查id
			return "null";
		} else if(flag.equals("new")){
			if(postListSize<=10){ //检查删除
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
				List<Integer> idsExited = PostProfileDao.checkExistedIds(idList);
				//获得不存在的id
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
		String deletedId = checkDeletedId("old", "2,3,7,8,36", 9);
		System.out.println(deletedId);
	}
}
