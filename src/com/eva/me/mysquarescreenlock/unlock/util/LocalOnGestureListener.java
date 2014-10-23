package com.eva.me.mysquarescreenlock.unlock.util;

import android.util.Log;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

public class LocalOnGestureListener extends SimpleOnGestureListener{
	
	private final int minFlingDistance = 50;
	public static int detectDirection = -1;
	
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
		
		
		if (Math.abs(velocityX) < Math.abs(velocityY)) {
			//根据向量判断获得方向的具体位置
			//进入这个判断，只能存在的可能是UP和DOWN两个方向
			if (velocityY < 0f) {
				//方向向上
				detectDirection = DIRECTION_UP;
				
			} else {
				//方向向下
				detectDirection = DIRECTION_DOWN;
						
			}
		} else if (Math.abs(velocityX) > Math.abs(velocityY)) {
			//进入这个判断，只能存在的可能是LEFT和RIGHT两个方向
			if (velocityX < 0f) {
				//方向向左
				detectDirection = DIRECTION_LEFT;
				
			} else {
				//方向向右
				detectDirection = DIRECTION_RIGHT;
				
			}
		} else {
			//进入这个判断，只能存在的可能是四个方向，就是角分线和某些特殊情况，所以可以考虑再次归零，然后再进行手势判断
			detectDirection = DIRECTION_CENTER;
		}
		Log.v(TAG, "detectDirection : "+detectDirection);
		
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
