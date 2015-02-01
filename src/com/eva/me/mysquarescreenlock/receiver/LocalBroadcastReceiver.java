package com.eva.me.mysquarescreenlock.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.eva.me.mysquarescreenlock.ScreenLockActivity;

public class LocalBroadcastReceiver extends BroadcastReceiver{
	private static final String TAG = "LocalBroadcastReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		String mAction = intent.getAction();
		
		Log.e(TAG, "The Intent Action is: "+mAction);
		
		if (mAction.equals(Intent.ACTION_SCREEN_ON)) {
			//when screen turn from OFF/* to ON
//			if ( !ScreenLockActivity.isShown) {
//				ScreenLockActivity.isShown = true;
//				Intent jmpSLA = new Intent();
//				jmpSLA.setClass(context, ScreenLockActivity.class);
//				jmpSLA.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				context.startActivity(jmpSLA);
//			}
		} else if (mAction.equals(Intent.ACTION_SCREEN_OFF)) {
			//when screen turn from ON/* to OFF
//			if (ScreenLockActivity.isShown) {
//				ScreenLockActivity.isShown = false;
//				((Activity) ScreenLockActivity .getInstance()).finish();
//			}

			if ( !ScreenLockActivity.isShown) {
				ScreenLockActivity.isShown = true;
				Intent jmpSLA = new Intent();
				jmpSLA.setClass(context, ScreenLockActivity.class);
				jmpSLA.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(jmpSLA);
			}
			
		}
	}

}
