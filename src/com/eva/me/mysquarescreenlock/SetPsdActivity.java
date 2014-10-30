package com.eva.me.mysquarescreenlock;

import com.eva.me.mysquarescreenlock.unlock.util.PasswordUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SetPsdActivity extends Activity {

	private Context context;
	private Button btnConfirm, btnClear, btnReset;
	
	private void showToast(String str) {
		Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_psw);
		init();
	}

	private void init() {
		//init views
		btnConfirm = (Button) findViewById(R.id.btnConfirm);
		btnReset = (Button) findViewById(R.id.btnReset);
		btnClear = (Button) findViewById(R.id.btnClear);
		
		//init context
		context = SetPsdActivity.this;
		
		//
		if (PasswordUtil.hasPsd(context)) {
			//有密码
			//那么先输入密码进行确认
			handleHasPsd();
		}else {
			//没有密码
			//新建密码，函数复用，在开始锁屏的时候没有检测到密码，也要新建密码
			handleNoPsd();
		}
	}

	private void setBtnVisible(boolean btnConfirmVisibility, boolean btnResetVisibility, boolean btnClearVisibility) {
		btnConfirm.setVisibility(btnConfirmVisibility? View.VISIBLE : View.INVISIBLE);
		btnReset.setVisibility(btnResetVisibility? View.VISIBLE : View.INVISIBLE);
		btnClear.setVisibility(btnClearVisibility? View.VISIBLE : View.INVISIBLE);
	}
	
	private void handleNoPsd() {
		showToast("没有密码，请先输入您的手势密码进行初始化！");
		setBtnVisible(true, false, false);
		
	}

	private void handleHasPsd() {
		showToast("请先输入密码确认您的身份！");
		setBtnVisible(true, false, true);//显示确认按钮和清空按钮
		btnConfirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set_psw, menu);
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
