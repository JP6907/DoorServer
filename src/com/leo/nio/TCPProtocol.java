package com.leo.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.neo.service.UserService;


public class TCPProtocol {
	private Charset charset = Charset.forName("utf-8");
	private int bufferSize;
	Selector selector;
	UserMap<String, SocketChannel> map;
	
	public TCPProtocol(Selector selector, int bufferSize){
		this.selector = selector;
		this.bufferSize = bufferSize;
		map = new UserMap<String, SocketChannel>();
	}
	
	/**
	 * 处理连接请求
	 * @param key
	 * @throws IOException
	 */
	public void handleAccept(SelectionKey key) throws IOException{
		// 得到与客户端通信的SocketChannel
		SocketChannel clientChannel = ((ServerSocketChannel)key.channel()).accept();
		// 非阻塞工作
		clientChannel.configureBlocking(false);
		// 注册并定义缓存的大小
		clientChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
	}
	
	public void handleRead(SelectionKey key)throws JSONException, IOException{
		// 得到与客户端通信的SocketChannel
		SocketChannel clientChannel = (SocketChannel)key.channel();
		// 得到缓存
		ByteBuffer buff = (ByteBuffer)key.attachment();
		String content = "";
		try {
			// 清空缓存, 做输入准备
			buff.clear();
			if(clientChannel.read(buff) == -1){          // 客户端异常退出
				clientChannel.close();
			}else{
				// 做输出准备
				buff.flip();
				content = charset.decode(buff).toString();
				System.out.println("****读取内容:" + content);
				// 准备接收下次读取请求
				key.interestOps(SelectionKey.OP_READ);
			}
		}catch (IOException e) {
			// 从selector中删除异常的SelectionKey
			key.cancel();
			if(clientChannel != null) {
				// 把对应的channel关闭
				clientChannel.close();
				// 从管理用户数的map移除
				map.removeByValue(clientChannel);
				System.out.println("****当前在线人数:" + map.size());
			}
			System.out.println("用户异常退出, 已取消注册");
		}
		
		if(content.startsWith(Protocol.CHAT_FLAG)){                       // 若是群聊
			JSONObject jObject = new JSONObject(content.substring(4));
			String sender = jObject.getString("SENDER");
			String reciever = jObject.getString("RECIEVER");
			if(reciever.equals("群聊")) {             // 输出到所有客户端
				writeToAll(content);
				System.out.println("****群聊");
			} else {
				writeToTheUser(sender, reciever, content);
				System.out.println("****私聊");
			}
			
		}
		if(content.startsWith(Protocol.LOGIN_FLAG)) {      // 用户登录
			String name = content.substring(4);
			System.out.println("****用户:"+name+"登录");
			String path = UserService.getPicPath(name);
			JSONArray json = new JSONArray().put(new JSONObject().put("USERNAME", name)
					 											 .put("PATH", path));
			// 通知所有用户
			writeToAll(json);
			// 通知登录用户,更新在线列表
			updateFriendList(map, clientChannel);
			// 保存用户
			map.put(name, clientChannel);                        
			System.out.println("****当前在线人数:" + map.size());
			
		}
		if(content.startsWith(Protocol.QUIT_FLAG)) {       // 用户退出
			String name = content.substring(4);
			System.out.println("****用户:"+name+"退出群聊");
			map.removeByValue(clientChannel);
			System.out.println("****当前在线人数:" + map.size());
			// 通知所有用户
			writeToAll(content);
		}				
		
	}
	

	private void writeToAll(String content) {
		if(content.length() > 0) {
			for(SocketChannel channel : map.valueSet()) {
				try {
					channel.write(charset.encode(content));
				} catch (Exception e) {
					System.out.println("输出异常:"+e.getMessage());
					map.removeByValue(channel);
					System.out.println("****当前在线人数:" + map.size());
					continue;
				}
			}
		}
	}
	
	private void writeToAll(JSONArray json) {
		if(json != null) {
			for(SocketChannel channel : map.valueSet()) {
				try {
					channel.write(charset.encode(Protocol.LOGIN_FLAG + json.toString()));
				} catch (Exception e) {
					System.out.println("###输出异常:"+e.getMessage());
					map.removeByValue(channel);
					System.out.println("****当前在线人数:" + map.size());
					continue;
				}
			}
		}
	}
	
	
	private void writeToTheUser(String sender, String reciever, String content) {
		SocketChannel senderChannel = map.getValueByKey(sender);
		SocketChannel recieverChannel = map.getValueByKey(reciever);
		try {
			if(senderChannel != null) {
				senderChannel.write(charset.encode(content));
			}	
		} catch (IOException e) {
			System.out.println("###输出异常:"+e.getMessage());
			map.removeByValue(senderChannel);
			System.out.println("****当前在线人数:" + map.size());
			e.printStackTrace();
		}
		try {
			if(recieverChannel != null) {
				recieverChannel.write(charset.encode(content));
			}	
		} catch (IOException e) {
			System.out.println("###输出异常:"+e.getMessage());
			map.removeByValue(recieverChannel);
			System.out.println("****当前在线人数:" + map.size());
			e.printStackTrace();
		}
	}
	
	private void updateFriendList(UserMap<String, SocketChannel> map, SocketChannel channel) 
			throws JSONException, IOException {
		// 遍历所有在线用户
		JSONArray json = new JSONArray();
		// 记录用户数
		json.put(new JSONObject().put("NUM", map.size()));       
		for(String name : map.keySet()) {
			String path = UserService.getPicPath(name);
			json.put(new JSONObject().put("USERNAME", name)
									 .put("PATH", path));
		}
		channel.write(charset.encode(Protocol.UPDATE_FLAG + json.toString()));
	} 
}
