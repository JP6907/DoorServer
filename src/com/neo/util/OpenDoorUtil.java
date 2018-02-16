package com.neo.util;

import java.util.Timer;
import java.util.TimerTask;

/**
 * ������ع�����
 * @author Administrator
 *
 */
public class OpenDoorUtil{
	/**
	 * ����״̬����
	 */
	public static boolean openDoorState = false;
	//����״̬����ʱ�� Ϊ7�� ��ʱ�䵽 openDoorState ״̬������λ
	private final static int KEEP_TIME = 7000;
	/**
	 * ����״̬����
	 * @param openDoorState
	 */
	public void openDoor(){
		this.setOpenDoorState(true);
		//������ʱ������ʱ��ԭ����״̬������ʱ��Ϊ  KEEP_TIME
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("��ʱ����ʼ����");
				setOpenDoorState(false);
				this.cancel();
				System.out.println("��ʱ��ȡ��");
				System.out.println(isOpenDoorState());
			}
		}, KEEP_TIME,1000);
	}
	/**
	 * ���ÿ���״̬����
	 * @param openDoorState
	 */
	public void setOpenDoorState(boolean openDoorState) {
		OpenDoorUtil.openDoorState = openDoorState;
	}
	/*
	 * ��ȡ����״̬
	 */
	public boolean isOpenDoorState() {
		return openDoorState;
	}
}

