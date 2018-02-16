package com.leo.nio;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 创建用来管理用户的map
 * @author leo
 * @param <K>
 * @param <V>
 */
public class UserMap<K,V> {
	// 创建线程安全的HashMap, 感觉不需要
	public Map<K, V> map = Collections.synchronizedMap(new HashMap<K, V>());
	
	/**
	 * 删除用户
	 * @param value
	 */
	public synchronized void removeByValue(V value) {
		for(K key : map.keySet()) {
			if(map.get(key) == value) {
				map.remove(key);
				break;
			}
		}
	}
	
	/**
	 * 根据用户的SocketChannel得到name
	 * @param val
	 * @return
	 */
	public synchronized K getKeyByValue(V val) {
		for(K key : map.keySet()) {
			if(map.get(key) == val || map.get(key).equals(val)) {
				return key;
			}
		}
		return null;
	}
	
	public synchronized V getValueByKey(K key) {
		return map.get(key);
	}
	
	/**
	 * 得到所有用户的name的集合
	 * @return
	 */
	public synchronized Set<K> keySet() {
		return map.keySet();
	}
	
	/**
	 * 得到所有在线用户对应的SocketChannel的集合
	 * @return
	 */
	public synchronized Set<V> valueSet() {
		Set<V> result = new HashSet<V>();
		for(K key : map.keySet()) {
			result.add(map.get(key));
		}
		return result;
	}
	
	/**
	 * 增加用户
	 * @param key
	 * @param value
	 * @return
	 */
	public synchronized V put(K key, V value) {
		for(V val : valueSet()) {
			if(val.equals(value) && val.hashCode()==value.hashCode()) {
				throw new RuntimeException("用户已存在!!");
			}
		}
		return map.put(key, value);
	}
	
	/**
	 * 返回用户在线人数
	 * @return
	 */
	public synchronized int size() {
		return map.size();
	}
	
}
