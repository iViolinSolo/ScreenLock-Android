package com.eva.me.mysquarescreenlock.service;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.eva.me.mysquarescreenlock.receiver.LocalBroadcastReceiver;

public class LocalService extends Service{
	public static final String TAG = "LocalService";
	
	private LocalBroadcastReceiver mReceiver = null;
	private Intent rebootIntent = null;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		
		mReceiver = new LocalBroadcastReceiver();
		
		registerReceiver(mReceiver, filter);
		
		KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
		KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("KeyguardLock");
		keyguardLock.disableKeyguard();
		
		super.onCreate();
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		rebootIntent = intent;
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {

		unregisterReceiver(mReceiver);
		
		if (rebootIntent != null) {
			startService(rebootIntent);
		}
		super.onDestroy();
	}
}
