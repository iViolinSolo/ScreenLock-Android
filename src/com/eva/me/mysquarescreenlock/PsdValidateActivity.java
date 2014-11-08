package com.eva.me.mysquarescreenlock;

import com.eva.me.mysquarescreenlock.unlock.util.PasswordUtil;
import com.eva.me.mysquarescreenlock.view.FlingRelativeLayout;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class PsdValidateActivity extends Activity {

	private final String TAG = "PsdValidateActivity";
	private Button btnConfirm, btnClear;
	private FlingRelativeLayout flingRelativeLayout;
	private Context context;
	private Intent iComeIn = null;
	private String order = "";
	
	private void showToast(String str, Context context) {
		Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_psd_validate);
		init();
	}

	private void init() {
		context = PsdValidateActivity.this;
		iComeIn = getIntent();
		order = iComeIn.getExtras().getString("order", "NoSuchOrder");
		
		//init views
		btnClear = (Button) findViewById(R.id.btnClear);
		btnConfirm = (Button) findViewById(R.id.btnConfirm);
		flingRelativeLayout = (FlingRelativeLayout) findViewById(R.id.flingRelativeLayout);
		
		btnClear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				PasswordUtil.curPsd = "";
				showToast("输入手势清除成功", context);
			}
		});
		
		btnConfirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//当前为验证密码的部分
				//首先执行密码验证功能，密码验证成功者会后才会进行其他的功能
				if (PasswordUtil.validatePsd(context)) {
					//密码正确
					showToast("验证成功~", context);
					PasswordUtil.curPsd = "";//清空当前是输入的密码
					//下面处理密码正确逻辑
					switch (order) {
					case "modify":
						//
						Log.e(TAG, "modify");
						break;
						
					case "delete":
						//
						Log.e(TAG, "delete");
						break;
						
					case "NoSuchOrder":
						Log.wtf(TAG, "NoSuchOrder....");
						break;
						
					default:
						break;
					}
					
				} else {
					//密码错误
					showToast("密码输入错误，请重新输入!", context);
					PasswordUtil.curPsd = "";//清空当前是输入的密码
				}
				
			}
		});
		
//		Intent cmIntent = getIntent();
//		order = cmIntent.getExtras().getString("order", "NoSuchOrder");
//		Log.v(TAG, "Order is "+order);
//		switch (order) {
//		case "modify":
//			
//			break;
//
//		case "delete":
//			
//			break;
//			
//		case "NoSuchOrder":
//			Log.wtf(TAG, "NoSuchOrder....");
//			break;
//
//		default:
//			break;
//		}
		
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
