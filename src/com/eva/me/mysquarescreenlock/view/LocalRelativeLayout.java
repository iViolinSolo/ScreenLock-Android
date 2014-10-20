package com.eva.me.mysquarescreenlock.view;

import android.app.Notification.Action;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class LocalRelativeLayout extends RelativeLayout{

	private Context mContext = null;
	private Bitmap dragView = null;//bitmap
	private final int initDragViewPos = 3000;//start pos
	private int dragViewX, dragViewY;//bitmap 
	
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
		//TODO: FINISH
	}

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			break;

		case MotionEvent.ACTION_MOVE:
			
			break;
		
		case MotionEvent.ACTION_UP:
			
			break;
			
		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	private void resetToInit() {
		
	}

	
}
