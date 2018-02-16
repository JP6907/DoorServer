package com.neo.util;

import java.util.List;

import com.neo.bean.PostProfile;

public class XmlHandle {
	/**
	 * 解析xml
	 * @param result
	 * @return
	 */
	public static String getXml(String result) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<BbsInformation>");
		sb.append("    <PostResult>" + result + "</PostResult>");
		sb.append("</BbsInformation>");
		return sb.toString();
	}
	/**
	 * 包装论坛帖子成xml格式
	 * @param postProfileList
	 * @param time
	 * @param deletedId
	 * @return
	 */
	public static String packPostDada(List<PostProfile> postProfileList, String time, String deletedId) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<BBSInformation>");
		sb.append("<Time>");
		sb.append("    <time>" + time + "</time>");
		sb.append("</Time>");
		sb.append("<DeletedId>");
		sb.append("    <deletedId>" + deletedId + "</deletedId>");
		sb.append("</DeletedId>");
		for (PostProfile postProfile : postProfileList) {
			sb.append("    <PostProfile>");
			sb.append("    <post_id>" + postProfile.post_id + "</post_id>");
			sb.append("    <post_title>" +  postProfile.post_title+ "</post_title>");
			sb.append("    <post_text>" + postProfile.post_text + "</post_text>");
			sb.append("    <post_publisher>" + postProfile.post_publisher + "</post_publisher>");
			sb.append("    <post_replynum>" + postProfile.post_replynum + "</post_replynum>");
			sb.append("    <post_publishdt>" + postProfile.post_publishdt + "</post_publishdt>");
			sb.append("    <post_newdt>" + postProfile.post_newdt + "</post_newdt>");
			sb.append("    <post_picture1>" + postProfile.post_picture1 + "</post_picture1>");
			sb.append("    <post_picture2>" + postProfile.post_picture2 + "</post_picture2>");
			sb.append("    <post_picture3>" + postProfile.post_picture3 + "</post_picture3>");
			sb.append("    <post_picture4>" + postProfile.post_picture4 + "</post_picture4>");
			sb.append("    <post_picture5>" + postProfile.post_picture5 + "</post_picture5>");
			sb.append("    <post_picture6>" + postProfile.post_picture6 + "</post_picture6>");
			sb.append("    <post_picture7>" + postProfile.post_picture7 + "</post_picture7>");
			sb.append("    <post_picture8>" + postProfile.post_picture8 + "</post_picture8>");
			sb.append("    <post_picture9>" + postProfile.post_picture9 + "</post_picture9>");
			sb.append("    <post_phone>" + postProfile.post_phone + "</post_phone>");
			sb.append("    </PostProfile>");
		}
		sb.append("</BBSInformation>");
		return sb.toString();
	}
}
