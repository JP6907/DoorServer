package com.neo.service;

import java.util.ArrayList;
import java.util.List;

import com.neo.bean.PostProfile;
import com.neo.bean.Reply;
import com.neo.dao.PostProfileDao;
import com.neo.dao.ReplyDao;
import com.neo.util.Timeutil;

/**
 * ��̳�����
 * @author Administrator
 *
 */
public class BBSService {
	/**
	 * ����ʱ����ȡ����
	 * @param flag �жϻ�ȡ�����ݻ��Ǿ�����
	 * @param time ʱ���
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
	 * ����ʱ����������ӵ�����
	 * @param time
	 * @return
	 */
	public static int countNewPostprofile(String time){
		return PostProfileDao.countNewPostProfilesByTime(time);
	}
	/**
	 * �������
	 * @param postProfile
	 * @return
	 */
	public static int addPostProfile(PostProfile postProfile){
		return PostProfileDao.addPostProfile(postProfile);
	}
	/**
	 * ��������id��ȡ���ӻظ�
	 * @param postId
	 * @return
	 */
	public static List<Reply> getReply(int postId){
		return ReplyDao.getReply(postId);
	}
	/**
	 * ��ӻظ�
	 * @param reply
	 * @return
	 */
	public static int addReply(Reply reply){
		return ReplyDao.addReply(reply);
	}
	/**
	 * ���ͼƬ�� PostProfile ��
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
	 * ���ͻ��˱��ػ����id�Ƿ���Ҫɾ��
	 * ���ͻ��˱��صĻ���id�ڷ��������Ƿ��Ѿ���ɾ��
	 * @param flag new or old  ��ȡ�����ݻ��Ǿ�����
	 * @param deleteId �ͻ��˻����id ��ʽ�� "id1,id2,id3..."
	 * @param postListSize ��ȡ�����ӵ�����
	 * @return
	 */
	public static String checkDeletedId(String flag, String deleteId, int postListSize){
		if(flag.equals("old")){  //����ǻ�ȡ�����ݣ��򲻼��id
			return "null";
		} else if(flag.equals("new")){
			if(postListSize<=10){ //���ɾ��
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
				List<Integer> idsExited = PostProfileDao.checkExistedIds(idList);
				//��ò����ڵ�id
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
		String deletedId = checkDeletedId("old", "2,3,7,8,36", 9);
		System.out.println(deletedId);
	}
}
