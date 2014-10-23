package com.eva.me.mysquarescreenlock.unlock.util;

import android.util.Log;

/**
 * 实现功能，坐标管理
 * @deprecated 暂时没有作用
 */
public class CoordinatesUtil {
	public static final int RADIUS = 50;
	private static final int DIRECTION_UP=1, 
			DIRECTION_RIGHT=2, 
			DIRECTION_DOWN=3, 
			DIRECTION_LEFT=4, 
			DIRECTION_DEFAULT=5,
			DIRECTION_CENTER=0;
	
	private final static String TAG= "CoordinatesUtil";
	//起始的位置，oriDrawView，左上角
	public static int startPosX = -1;
	public static int startPosY = -1;
	
//	//oriDrawView基本信息，长宽
//	public static int width;
//	public static int height;
//	
//	//oriDrawView
	
	public static int getDirection(int dragViewX, int dragViewY ) {
		Log.e(TAG, "DragViewX: "+dragViewX+" DragViewY: "+dragViewY+" StartViewX: "+startPosX+" StartViewY: "+startPosY);
		
		int xTmp = Math.abs(dragViewX-startPosX);
		int yTmp = Math.abs(dragViewY-startPosY);
		Log.e(TAG, "xTmp: "+xTmp+" yTmp: "+yTmp);
		if (xTmp<= RADIUS && yTmp<=RADIUS) {
			//当这个时候的最大差没有超过半径的时候，不做任何动作，只是简单地跟随手的坐标（也就是指的是最后的ACTION_MOVE时候的），也就是说最后onDraw时候的绘画的横纵坐标就是手指的坐标
			return DIRECTION_CENTER;
		}else {
			//如果这个时候最后手指的滑动范围超过了半径，那么最后在ondraw的时候会根据最后究竟是四个方向的那个方向进行最后的不同的坐标赋值方法
			if (xTmp<=RADIUS && yTmp>RADIUS) {
//				return 1,3;
				if (dragViewY > startPosY) {
					return DIRECTION_UP;
				} else if (dragViewY < startPosY) {
					return DIRECTION_DOWN;
				} else {
					Log.wtf(TAG, "UNKONWN ERROR~ in getDirection");
					return -1;
				}
			
			} else if (xTmp>RADIUS && yTmp<=RADIUS) {
//				return 2,4;
				if (dragViewX > startPosX) {
					return DIRECTION_RIGHT;
				} else if (dragViewX < startPosX) {
					return DIRECTION_LEFT;
				} else {
					Log.wtf(TAG, "UNKONWN ERROR~ in getDirection");
					return -1;
				}
				
			} else {
				return DIRECTION_CENTER;
			}
		}
	}
	
}
