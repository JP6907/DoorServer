package com.leo.nio;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * �������������û���map
 * @author leo
 * @param <K>
 * @param <V>
 */
public class UserMap<K,V> {
	// �����̰߳�ȫ��HashMap, �о�����Ҫ
	public Map<K, V> map = Collections.synchronizedMap(new HashMap<K, V>());
	
	/**
	 * ɾ���û�
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
	 * �����û���SocketChannel�õ�name
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
	 * �õ������û���name�ļ���
	 * @return
	 */
	public synchronized Set<K> keySet() {
		return map.keySet();
	}
	
	/**
	 * �õ����������û���Ӧ��SocketChannel�ļ���
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
	 * �����û�
	 * @param key
	 * @param value
	 * @return
	 */
	public synchronized V put(K key, V value) {
		for(V val : valueSet()) {
			if(val.equals(value) && val.hashCode()==value.hashCode()) {
				throw new RuntimeException("�û��Ѵ���!!");
			}
		}
		return map.put(key, value);
	}
	
	/**
	 * �����û���������
	 * @return
	 */
	public synchronized int size() {
		return map.size();
	}
	
}
