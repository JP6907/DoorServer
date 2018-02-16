package com.leo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

import org.json.JSONException;

public class NIOServer {
	// Selector管理Channel
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
				.open()                                                        // 创建
				.bind(new InetSocketAddress(IP, PORT));            			   // 绑定端口
		// 非阻塞工作
		serverChannel.configureBlocking(false);
		// 将serverChanner注册到Selector中
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);              // 接收模式
		// 创建一个处理IO操作的协议
		protocol = new TCPProtocol(selector, BUFFERSIZE);
		
		System.out.println("****服务器启动");
		
		while(true){
			if(selector.select(TIMEOUT) == 0){       // 没有请求
				continue;
			}
			// 得到Selected-key的迭代器, 此包含需要进行IO操作的channel
			Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
			// 遍历所有需要进行IO操作的Selectionkey
			while(keyIterator.hasNext()){  
				SelectionKey key = keyIterator.next();
				// 移除处理过的key
				keyIterator.remove();
				try {
					if(key.isAcceptable()){
						protocol.handleAccept(key);
					}
					if(key.isReadable()){
						protocol.handleRead(key);
					}
				} catch (IOException e) {
					System.out.println("IO异常:" + e.getMessage());
					System.out.println("有用户登出,当前用户数:" + (selector.keys().size() - 1));
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
