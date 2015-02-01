package com.eva.me.mysquarescreenlock.lockutil;

import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class LockLayer {
	private Activity mActivty;
	private WindowManager mWindowManager;
	private View mLockView;
	private LayoutParams mLockViewLayoutParams;
	private static LockLayer mLockLayer;
	private boolean isLocked;
	
	public static synchronized LockLayer getInstance(Activity act){
		if(mLockLayer == null){
			mLockLayer = new LockLayer(act);
		}
		return mLockLayer;
	}
	
	private LockLayer(Activity act) {
		mActivty = act;
		init();
	}

	private void init(){
		isLocked = false;
		mWindowManager = mActivty.getWindowManager();
		mLockViewLayoutParams = new LayoutParams();
		mLockViewLayoutParams.width = LayoutParams.MATCH_PARENT;
		mLockViewLayoutParams.height = LayoutParams.MATCH_PARENT;
		//实现关键
		mLockViewLayoutParams.type = LayoutParams.TYPE_SYSTEM_ERROR;
		// 此行代码有时在主界面键按下情况下会出现无法显示和退出，暂时去掉，去掉之后按下主界面键会直接返回主界面

		//apktool value，这个值具体是哪个变量还请网友帮忙
		//mLockViewLayoutParams.flags = 1280;
	}
	public synchronized void lock() {
		if(mLockView!=null && !isLocked){
			mWindowManager.addView(mLockView, mLockViewLayoutParams);
		}
		isLocked = true;
	}
	public synchronized void unlock() {
		if(mWindowManager!=null && isLocked){
			mWindowManager.removeView(mLockView);
		}
		isLocked = false;
	}
	public synchronized void setLockView(View v){
		mLockView = v;
	}
}
