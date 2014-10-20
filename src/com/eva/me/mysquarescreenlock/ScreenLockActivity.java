package com.eva.me.mysquarescreenlock;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ScreenLockActivity extends Activity {
	private static final String TAG = "ScreenLockActivity";
	public static boolean isShown = false;
	private static Context instance = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		isShown = true;
		instance = ScreenLockActivity.this;
		setContentView(R.layout.activity_screen_lock);
		init();
	}
	
	private void init() {
		
	}
	

	@Override
	protected void onDestroy() {
		isShown = false;
		instance = null;
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.screen_lock, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
	public static Context getInstance() {
		return instance;
	}
}
