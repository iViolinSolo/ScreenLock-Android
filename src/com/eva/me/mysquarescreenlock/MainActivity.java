package com.eva.me.mysquarescreenlock;

import com.eva.me.mysquarescreenlock.unlock.util.PasswordUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	
	private Button btnStaSer;
	private Button btnStaPsdSetting;
	private Context context;
	
	private void showToast(String str,Context context) {
		Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		context = MainActivity.this;
		
		btnStaSer = (Button) findViewById(R.id.button1);
		btnStaSer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent jmpLS = new Intent();
				jmpLS.setAction("com.eva.service.LocalService");
				MainActivity.this.startService(jmpLS);
				showToast("开启锁屏~", MainActivity.this);
//				PasswordUtil.setDefaultPsd(context);//这个是测试用，初始密码
			}
		});
		
		btnStaPsdSetting = (Button) findViewById(R.id.button2);
		btnStaPsdSetting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent jmpPS = new Intent(MainActivity.this, SetPsdActivity.class);
				startActivity(jmpPS);
				showToast("这里进行手势密码设置", MainActivity.this);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
