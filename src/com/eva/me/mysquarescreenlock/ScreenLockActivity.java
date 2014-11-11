package com.eva.me.mysquarescreenlock;

import com.eva.me.mysquarescreenlock.listener.OnFlingCompleteListener;
import com.eva.me.mysquarescreenlock.unlock.util.PasswordUtil;
import com.eva.me.mysquarescreenlock.view.FlingRelativeLayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScreenLockActivity extends Activity {
	private static final String TAG = "ScreenLockActivity";
	public static boolean isShown = false;
	private static Context instance = null;
	
	private final int NEED_DELAY = 1;
	private long delay = 100l;
	
	private Button btnConfirm, btnClear;
	private TextView tvTopInfo, tvPsdReveal;
	private FlingRelativeLayout flingRelativeLayout;
	
	private void showToast(String str, Context context) {
		Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		isShown = true;
		instance = ScreenLockActivity.this;
		setContentView(R.layout.activity_screen_lock);
		init();
	}
	
	private void init() {
		Log.e(TAG, "ScreenLockActivity-> onCreate -> init");
		
		//Init Views
		flingRelativeLayout = (FlingRelativeLayout) findViewById(R.id.flingRelativeLayout);
		btnConfirm = (Button) findViewById(R.id.btnConfirm);
		btnClear = (Button) findViewById(R.id.btnClear);
		tvTopInfo = (TextView) findViewById(R.id.tvTopInfo);
		tvPsdReveal = (TextView) findViewById(R.id.tvPsdReveal);
		
		tvPsdReveal.setText("");

		if (PasswordUtil.hasPsd(instance)) {
			//有密码的时候
			btnConfirm.setVisibility(View.VISIBLE);
			btnClear.setVisibility(View.VISIBLE);
			tvTopInfo.setText("请输入您的手势密码来解锁设备：");
			
			flingRelativeLayout.setOnFlingCompleteListener(new OnFlingCompleteListener() {
				
				@Override
				public void onFlingComplete(int curDirection) {
					//每次手势结束时候触发
					if (!PasswordUtil.curPsd.equals("")) {
						tvPsdReveal.setText(""+PasswordUtil.curPsd);
					}
				}
			});
						
			btnConfirm.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (PasswordUtil.validatePsd(instance)) {
						//密码输入正确
						showToast("密码输入正确，欢迎回来~", instance);
						PasswordUtil.curPsd="";//每次都是自动帮你去清空整个密码
						tvPsdReveal.setText("");
						finish();
					} else {
						//密码错误
						//清空密码
						PasswordUtil.curPsd="";
						tvPsdReveal.setText("");
						showToast("密码输入错误，请重新输入", instance);
					}
				}
			});
			
			btnClear.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					PasswordUtil.curPsd = "";
					tvPsdReveal.setText("");
					showToast("已清空输入的密码", instance);
				}
			});
			
		} else {
			//没有密码的时候
			btnConfirm.setVisibility(View.INVISIBLE);
			btnClear.setVisibility(View.INVISIBLE);
			tvTopInfo.setText("任意方向滑动即可解锁~");
			flingRelativeLayout.setOnFlingCompleteListener(new OnFlingCompleteListener() {
				
				@Override
				public void onFlingComplete(int curDirection) {
					Log.e(TAG, "curDirection: "+curDirection);
					//一旦监听到有滑动手势的时候，最好判断一下，不为0，就可以finish()
					if (curDirection != 0) {
						PasswordUtil.curPsd="";//清空，因为如果不清空的话，每次滑动其实是把我们的滑动代码存储了起来
						mHandler.obtainMessage(NEED_DELAY).sendToTarget();
//						ScreenLockActivity.this.finish();//比起直接结束，效果更好
					}
				}
			});
			
		}
		
	}
	
	
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case NEED_DELAY:
				mHandler.postDelayed(finishCurAct, delay);
				break;

			default:
				break;
			}
		};
	};
	
	private Runnable finishCurAct = new Runnable() {
		
		@Override
		public void run() {
			ScreenLockActivity.this.finish();
			showToast("解锁成功~", ScreenLockActivity.this);
		}
	};

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
