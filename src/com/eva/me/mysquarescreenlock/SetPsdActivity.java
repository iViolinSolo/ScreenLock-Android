package com.eva.me.mysquarescreenlock;

import com.eva.me.mysquarescreenlock.unlock.util.PasswordUtil;

import android.R.integer;
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

public class SetPsdActivity extends Activity {

	private Context context;
	private Button btnModify, btnDelete, btnOpen;
	
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
		context = SetPsdActivity.this;
		//init buttons
		btnOpen =(Button) findViewById(R.id.btnOpen);
		btnDelete = (Button) findViewById(R.id.btnDelete);
		btnModify = (Button) findViewById(R.id.btnModify);
		
		if (PasswordUtil.hasPsd(context)) {
			//有密码
			handleHasPsd();
		} else {
			//没有密码
			handleNoPsd();
		}
		
	}

	private void handleNoPsd() {
		//display or not display
		btnModify.setVisibility(View.GONE);//更改为GONE 居中而不是invisible
		btnDelete.setVisibility(View.GONE);
		btnOpen.setVisibility(View.VISIBLE);
		
		btnOpen.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent iJmpPRA = new Intent(context, PsdResetActivity.class);
				startActivity(iJmpPRA);
				finish();
			}
		});
		
	}

	private void handleHasPsd() {
		//display or not display
		btnModify.setVisibility(View.VISIBLE);
		btnDelete.setVisibility(View.VISIBLE);
		btnOpen.setVisibility(View.GONE);
		
		//set button click listener
		//不论按那个，全部都是进入验证界面，但是只不过传入的参数不同
		btnModify.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent jmpPVA  = new Intent();
				jmpPVA.setClass(context, PsdValidateActivity.class);
				jmpPVA.putExtra("order", "modify");
				startActivity(jmpPVA);
				finish();
			}
		});
		
		btnDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent jmpPVA  = new Intent();
				jmpPVA.setClass(context, PsdValidateActivity.class);
				jmpPVA.putExtra("order", "delete");
				startActivity(jmpPVA);
				finish();
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
