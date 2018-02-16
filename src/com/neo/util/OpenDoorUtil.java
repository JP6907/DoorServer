package com.neo.util;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 开门相关工具类
 * @author Administrator
 *
 */
public class OpenDoorUtil{
	/**
	 * 开门状态变量
	 */
	public static boolean openDoorState = false;
	//开门状态保持时间 为7秒 ，时间到 openDoorState 状态变量复位
	private final static int KEEP_TIME = 7000;
	/**
	 * 设置状态变量
	 * @param openDoorState
	 */
	public void openDoor(){
		this.setOpenDoorState(true);
		//开启定时器，定时还原开门状态，设置时间为  KEEP_TIME
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("定时器开始工作");
				setOpenDoorState(false);
				this.cancel();
				System.out.println("定时器取消");
				System.out.println(isOpenDoorState());
			}
		}, KEEP_TIME,1000);
	}
	/**
	 * 设置开门状态变量
	 * @param openDoorState
	 */
	public void setOpenDoorState(boolean openDoorState) {
		OpenDoorUtil.openDoorState = openDoorState;
	}
	/*
	 * 获取开门状态
	 */
	public boolean isOpenDoorState() {
		return openDoorState;
	}
}

