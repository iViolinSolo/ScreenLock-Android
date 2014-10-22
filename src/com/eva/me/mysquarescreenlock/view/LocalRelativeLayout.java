package com.eva.me.mysquarescreenlock.view;

import com.eva.me.mysquarescreenlock.unlock.util.CoordinatesUtil;

import android.R;
import android.app.Notification.Action;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LocalRelativeLayout extends RelativeLayout{

	private final String TAG = "LocalRelativeLayout";//TAG
	
	private Context mContext = null;
	private Bitmap dragView = null;//bitmap
	private final int initDragViewPos = 3000;//start pos
	private int dragViewX = initDragViewPos
			, dragViewY = initDragViewPos;       //bitmap pos
	private ImageView oriDraView = null;//original drag view in center
//	private int startPosX, startPosY;//center pos
	private int curDirection = 0;//初始化的方向就是最开始的方向
	
	
	//CONSTRUCTORS
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
					com.eva.me.mysquarescreenlock.R.drawable.ic_lockscreen_handle_pressed);
		}
	}

	//init the origin drag view
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		Log.d(TAG, "onFinishInflate: ");
		oriDraView = (ImageView) findViewById(com.eva.me.mysquarescreenlock.R.id.ivOriginDragView);//initialize
		//init startPos
		CoordinatesUtil.startPosX = oriDraView.getLeft();
		CoordinatesUtil.startPosY = oriDraView.getTop();
	}

	//On Draw
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Log.v(TAG, "===========OnDraw===========");
		initOnDraw(canvas);
	}

	//init switch logic
	private void initOnDraw(Canvas canvas) {
		// TODO Auto-generated method stub

		int left = 0, top = 0;
		
		//根据究竟是那个方向进行不同的绘制方法
		switch (curDirection) {
		case 0://就在中央位置，所以绘制的坐标就是手指的坐标
			left = dragViewX;
			top = dragViewY;
			break;
			
		case 1://手指这个时候在向上滑动，所以横坐标固定
			left = CoordinatesUtil.startPosX;
			top = dragViewY;
			break;
			
		case 2://手指向向右滑动，所以纵坐标不变
			left = dragViewX;
			top = CoordinatesUtil.startPosY;
			break;
			
		case 3://手指向下滑动，所以横坐标不变
			left = CoordinatesUtil.startPosX;
			top = dragViewY;
			break;
			
		case 4://手指向左滑动，所以这个时候纵坐标不变
			left = dragViewX;
			top = CoordinatesUtil.startPosY;
			break;

		default:
			Log.wtf(TAG, "unknown exception in switch loop --> curDirection : "+curDirection);
			break;
		}
		Log.e(TAG, "OnDraw : ==> left: "+left+" top: "+top);
		canvas.drawBitmap(dragView, left, top, null);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.v(TAG, "onTouchEvent: ACTION_DOWN");
			//initialize pos
			dragViewX = (int) event.getX();
			dragViewY = (int) event.getY();
			
			return handleActionDown(event);
		
		case MotionEvent.ACTION_MOVE:
			Log.v(TAG, "onTouchEvent: ACTION_MOVE");
			//update pos
			dragViewX = (int) event.getX();
			dragViewY = (int) event.getY();
			
			handleActionMove(event);
			return true;
		
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

	//control action move event
	private void handleActionMove(MotionEvent event) {
		curDirection = CoordinatesUtil.getDirection(dragViewX, dragViewY);//get direction
		invalidate();
	}

	//control action down event
	private boolean handleActionDown(MotionEvent event) {
		Rect rect = new Rect();
		oriDraView.getHitRect(rect);
		int x = (int) event.getX();
		int y = (int) event.getY();
		
		boolean isInRect = rect.contains(x, y);
		
		if(isInRect) {
			oriDraView.setVisibility(View.INVISIBLE);
		}
		
		return isInRect;
		
	}

	private void resetToInit() {
		
	}

	
}
