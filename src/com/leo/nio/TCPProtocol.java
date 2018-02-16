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
	 * ������������
	 * @param key
	 * @throws IOException
	 */
	public void handleAccept(SelectionKey key) throws IOException{
		// �õ���ͻ���ͨ�ŵ�SocketChannel
		SocketChannel clientChannel = ((ServerSocketChannel)key.channel()).accept();
		// ����������
		clientChannel.configureBlocking(false);
		// ע�Ტ���建��Ĵ�С
		clientChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
	}
	
	public void handleRead(SelectionKey key)throws JSONException, IOException{
		// �õ���ͻ���ͨ�ŵ�SocketChannel
		SocketChannel clientChannel = (SocketChannel)key.channel();
		// �õ�����
		ByteBuffer buff = (ByteBuffer)key.attachment();
		String content = "";
		try {
			// ��ջ���, ������׼��
			buff.clear();
			if(clientChannel.read(buff) == -1){          // �ͻ����쳣�˳�
				clientChannel.close();
			}else{
				// �����׼��
				buff.flip();
				content = charset.decode(buff).toString();
				System.out.println("****��ȡ����:" + content);
				// ׼�������´ζ�ȡ����
				key.interestOps(SelectionKey.OP_READ);
			}
		}catch (IOException e) {
			// ��selector��ɾ���쳣��SelectionKey
			key.cancel();
			if(clientChannel != null) {
				// �Ѷ�Ӧ��channel�ر�
				clientChannel.close();
				// �ӹ����û�����map�Ƴ�
				map.removeByValue(clientChannel);
				System.out.println("****��ǰ��������:" + map.size());
			}
			System.out.println("�û��쳣�˳�, ��ȡ��ע��");
		}
		
		if(content.startsWith(Protocol.CHAT_FLAG)){                       // ����Ⱥ��
			JSONObject jObject = new JSONObject(content.substring(4));
			String sender = jObject.getString("SENDER");
			String reciever = jObject.getString("RECIEVER");
			if(reciever.equals("Ⱥ��")) {             // ��������пͻ���
				writeToAll(content);
				System.out.println("****Ⱥ��");
			} else {
				writeToTheUser(sender, reciever, content);
				System.out.println("****˽��");
			}
			
		}
		if(content.startsWith(Protocol.LOGIN_FLAG)) {      // �û���¼
			String name = content.substring(4);
			System.out.println("****�û�:"+name+"��¼");
			String path = UserService.getPicPath(name);
			JSONArray json = new JSONArray().put(new JSONObject().put("USERNAME", name)
					 											 .put("PATH", path));
			// ֪ͨ�����û�
			writeToAll(json);
			// ֪ͨ��¼�û�,���������б�
			updateFriendList(map, clientChannel);
			// �����û�
			map.put(name, clientChannel);                        
			System.out.println("****��ǰ��������:" + map.size());
			
		}
		if(content.startsWith(Protocol.QUIT_FLAG)) {       // �û��˳�
			String name = content.substring(4);
			System.out.println("****�û�:"+name+"�˳�Ⱥ��");
			map.removeByValue(clientChannel);
			System.out.println("****��ǰ��������:" + map.size());
			// ֪ͨ�����û�
			writeToAll(content);
		}				
		
	}
	

	private void writeToAll(String content) {
		if(content.length() > 0) {
			for(SocketChannel channel : map.valueSet()) {
				try {
					channel.write(charset.encode(content));
				} catch (Exception e) {
					System.out.println("����쳣:"+e.getMessage());
					map.removeByValue(channel);
					System.out.println("****��ǰ��������:" + map.size());
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
					System.out.println("###����쳣:"+e.getMessage());
					map.removeByValue(channel);
					System.out.println("****��ǰ��������:" + map.size());
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
			System.out.println("###����쳣:"+e.getMessage());
			map.removeByValue(senderChannel);
			System.out.println("****��ǰ��������:" + map.size());
			e.printStackTrace();
		}
		try {
			if(recieverChannel != null) {
				recieverChannel.write(charset.encode(content));
			}	
		} catch (IOException e) {
			System.out.println("###����쳣:"+e.getMessage());
			map.removeByValue(recieverChannel);
			System.out.println("****��ǰ��������:" + map.size());
			e.printStackTrace();
		}
	}
	
	private void updateFriendList(UserMap<String, SocketChannel> map, SocketChannel channel) 
			throws JSONException, IOException {
		// �������������û�
		JSONArray json = new JSONArray();
		// ��¼�û���
		json.put(new JSONObject().put("NUM", map.size()));       
		for(String name : map.keySet()) {
			String path = UserService.getPicPath(name);
			json.put(new JSONObject().put("USERNAME", name)
									 .put("PATH", path));
		}
		channel.write(charset.encode(Protocol.UPDATE_FLAG + json.toString()));
	} 
}
