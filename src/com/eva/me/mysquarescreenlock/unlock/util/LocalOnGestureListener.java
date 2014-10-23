package com.eva.me.mysquarescreenlock.unlock.util;

import android.util.Log;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

public class LocalOnGestureListener extends SimpleOnGestureListener{
	
	private final int minFlingDistance = 50;
	public static int DetectDirection = -1;
	
	private static final int DIRECTION_UP=1, 
			DIRECTION_RIGHT=2, 
			DIRECTION_DOWN=3, 
			DIRECTION_LEFT=4, 
			DIRECTION_CENTER=0;
	
	private static final String TAG = "LocalOnGestureListener";
	
	
	@Override
 	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
//		return super.onDown(e);
		return true;//必须设定为true才会触发后面的onfling的那个重载函数
	}
	
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {	
		//TODO ==============>>>>>>>>>>
		Log.v(TAG, "LocalOnGestureListener: OnFling()");
		handleFlingDirection(e1, e2, velocityX, velocityY);
		return super.onFling(e1, e2, velocityX, velocityY);
	}
	
	/**
	 * 用来处理onFling的四个参数，这样得到方向
	 * 
	 * @param e1 起始的event
	 * @param e2 终止的时候的event
	 * @param velocityX 加速度，如果手势向右的时候为﹢，手势向左为-
	 * @param velocityY 加速度，如果手势向下的时候为﹢，手势向右为-
	 * 
	 */
	private void handleFlingDirection(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		float e1X = e1.getX(), e1Y = e1.getY();
		float e2X = e2.getX(), e2Y = e2.getY();
		
		Log.d(TAG, "e1X: "+e1X+" e1Y: "+e1Y+"\n"
					+"e2X: "+e2X +" e2Y: "+e2Y+"\n"
					+"velocityX: "+velocityX+" velocityY: "+velocityY);
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return super.onScroll(e1, e2, distanceX, distanceY);
	}
	
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		super.onLongPress(e);
	}
	
	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		super.onShowPress(e);
	}
}
