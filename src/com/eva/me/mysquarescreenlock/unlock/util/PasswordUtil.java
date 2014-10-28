package com.eva.me.mysquarescreenlock.unlock.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class PasswordUtil {
	private static String psd = "";
	public static String curPsd = "";
	public static final String spName = "com.eva.me.sharedpreferences.psd";
	private static boolean hasPsd = false;
	private static final String TAG= "PasswordUtil";
	
	/**
	 * get psd from sharedpreferences
	 * @param context
	 * @return
	 */
	public static String getPsd(Context context) {
		if (psd.equals("")) {
			//get real psd from sharedPreference
			SharedPreferences sharedPreferences = context.getSharedPreferences(spName, Activity.MODE_PRIVATE); 
			hasPsd = sharedPreferences.getBoolean("hasPsd", false);//默认是false,存起来
			if (hasPsd) {
				psd= sharedPreferences.getString("psd", "");//默认是空字符串
				Log.e(TAG, "Password: "+psd);
			}
		}
		return psd;
	}
	
	/**
	 * set newPsd to be a new psd
	 * @param newPsd
	 * @param context
	 * @return
	 */
	public static boolean setPsd(String newPsd, Context context) {
		psd = newPsd;
		hasPsd = true;
		//实现接下来的SharePreference
		SharedPreferences sharedPreferences = context.getSharedPreferences(spName, Activity.MODE_PRIVATE); 
		Editor editor = sharedPreferences.edit();
		editor.putString("psd", psd);
		editor.putBoolean("hasPsd", hasPsd);
		editor.commit();
		Log.e(TAG, "Password: "+psd);
		
		return true;
	}
	/**
	 * clear password if it exists
	 * 
	 * @param context
	 * @return
	 */
	public static boolean clearPsd(Context context) {
		if (hasPsd) {
			psd="";
			hasPsd=false;
			
			SharedPreferences sharedPreferences = context.getSharedPreferences(spName, Activity.MODE_PRIVATE); 
			Editor editor = sharedPreferences.edit();
			editor.putString("psd", psd);
			editor.putBoolean("hasPsd", hasPsd);
			editor.commit();
			Log.e(TAG, "Password: "+psd);
		}

		return true;
	}
	/**
	 * return hasPsd
	 * @param context
	 * @return
	 */
	public static boolean hasPsd(Context context) {
		if (!hasPsd) {
			//如果本身hasPsd就是false的，那么首先要获得一些
			SharedPreferences sharedPreferences = context.getSharedPreferences(spName, Activity.MODE_PRIVATE); 
			hasPsd  = sharedPreferences.getBoolean("hasPsd", false);
			Log.e(TAG, "hasPsd: "+hasPsd);
		}
		return hasPsd;
	}
	
	
}
