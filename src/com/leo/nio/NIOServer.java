package com.leo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

import org.json.JSONException;

public class NIOServer {
	// Selector����Channel
	private Selector selector = null;
	TCPProtocol protocol;
	private final int PORT = 30011;
	private final int TIMEOUT = 6000;
	private final String IP = "123.207.117.75";
	//private final String IP = "192.168.155.1";
	private final int BUFFERSIZE = 1024;
	
	
	public void init() throws IOException{
		selector = Selector.open();
		ServerSocketChannel serverChannel = ServerSocketChannel
				.open()                                                        // ����
				.bind(new InetSocketAddress(IP, PORT));            			   // �󶨶˿�
		// ����������
		serverChannel.configureBlocking(false);
		// ��serverChannerע�ᵽSelector��
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);              // ����ģʽ
		// ����һ������IO������Э��
		protocol = new TCPProtocol(selector, BUFFERSIZE);
		
		System.out.println("****����������");
		
		while(true){
			if(selector.select(TIMEOUT) == 0){       // û������
				continue;
			}
			// �õ�Selected-key�ĵ�����, �˰�����Ҫ����IO������channel
			Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
			// ����������Ҫ����IO������Selectionkey
			while(keyIterator.hasNext()){  
				SelectionKey key = keyIterator.next();
				// �Ƴ��������key
				keyIterator.remove();
				try {
					if(key.isAcceptable()){
						protocol.handleAccept(key);
					}
					if(key.isReadable()){
						protocol.handleRead(key);
					}
				} catch (IOException e) {
					System.out.println("IO�쳣:" + e.getMessage());
					System.out.println("���û��ǳ�,��ǰ�û���:" + (selector.keys().size() - 1));
					e.printStackTrace();
					continue;
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
	}
		
	public static void main(String[] args) throws IOException {
		new NIOServer().init();
	}
}
