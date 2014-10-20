package com.eva.me.mysquarescreenlock.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class LocalRelativeLayout extends RelativeLayout{

	private Context mContext = null;
	private Bitmap dragView = null;
	
	public LocalRelativeLayout(Context context) {
		super(context);
		mContext = context;
		initDragBitmap();		
	}

	public LocalRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initDragBitmap();		
	}
	
	public LocalRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		initDragBitmap();		
	}

	private void initDragBitmap() {		
	}
	

}
