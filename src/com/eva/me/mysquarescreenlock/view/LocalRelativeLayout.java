package com.eva.me.mysquarescreenlock.view;

import android.R;
import android.app.Notification.Action;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

	//init the drag view
	private void initDragBitmap() {	
		if (dragView == null) {
			dragView = BitmapFactory.decodeResource(mContext.getResources(),
					com.eva.me.mysquarescreenlock.R.drawable.ic_lockscreen_handle_normal);
		}
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
			handleActionDown();
			break;

		case MotionEvent.ACTION_MOVE:
			handleActionMove();
			break;
		
		case MotionEvent.ACTION_UP:
			handleActionUp();
			break;
			
		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	private void handleActionUp() {
		// TODO Auto-generated method stub
		
	}

	private void handleActionMove() {
		// TODO Auto-generated method stub
		
	}

	private void handleActionDown() {
		// TODO Auto-generated method stub
		
	}

	private void resetToInit() {
		
	}

	
}
