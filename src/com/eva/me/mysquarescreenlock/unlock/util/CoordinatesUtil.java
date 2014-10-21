package com.eva.me.mysquarescreenlock.unlock.util;

import android.util.Log;

/**
 * 实现功能，坐标管理
 * 
 */
public class CoordinatesUtil {
	private static final int RADIUS = 10;
	private static final int DIRECTION_UP=1, 
			DIRECTION_RIGHT=2, 
			DIRECTION_DOWN=3, 
			DIRECTION_LEFT=4, 
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
		getMaxLen(dragViewX, dragViewY);
		
		return 0;
	}


private static int getMaxLen(int dragViewX, int dragViewY) {
	
	
}
	
	
	
}
