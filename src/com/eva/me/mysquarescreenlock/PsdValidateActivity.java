package com.eva.me.mysquarescreenlock;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class PsdValidateActivity extends Activity {

	private final String TAG = "PsdValidateActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_psd_validate);
		init();
	}

	private void init() {
		//init views
		
		
		Intent cmIntent = getIntent();
		String order = cmIntent.getExtras().getString("order", "NoSuchOrder");
		Log.v(TAG, "Order is "+order);
		
		
		switch (order) {
		case "modify":
			
			break;

		case "delete":
			
			break;
			
		case "NoSuchOrder":
			
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.psd_validate, menu);
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
}
