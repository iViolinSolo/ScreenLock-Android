package com.eva.me.mysquarescreenlock.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.eva.me.mysquarescreenlock.unlock.util.CoordinatesUtil;
import com.eva.me.mysquarescreenlock.unlock.util.LocalOnGestureListener;

public class FlingRelativeLayout extends RelativeLayout{
	public static final int GET_DIRECTION = 10086;

	private final String TAG = "FlingRelativeLayout";//TAG
	
	private Context mContext = null;
	
	private Bitmap dragView = null;//bitmap
	private final int initDragViewPos = 3000;//start pos
	private int dragViewX = initDragViewPos
			, dragViewY = initDragViewPos;       //bitmap pos
	
	private ImageView oriDraView;//original drag view in center
	private int startPosX = -1, startPosY = -1;//center pos
	
	private int curDirection = 0;//初始化的方向就是最开始的方向
	
	private GestureDetector gestureDetector = null;
	
	
	//CONSTRUCTORS
 	public FlingRelativeLayout(Context context) {
		super(context);
		initConstructor(context);
	}

	public FlingRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initConstructor(context);
	}
	
	public FlingRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initConstructor(context);
	}

	private void initConstructor(Context context) {
		mContext = context;
		LocalOnGestureListener localOnGestureListener = new LocalOnGestureListener(); 
		gestureDetector = new GestureDetector(mContext, localOnGestureListener);
		localOnGestureListener.setHandler(mHandler);
		
		initDragBitmap();	
		
	}
	
	//init the drag view
	private void initDragBitmap() {	
		if (dragView == null) {
			dragView = BitmapFactory.decodeResource(mContext.getResources(),
					com.eva.me.mysquarescreenlock.R.drawable.ring);
		}
	}

	//init the origin drag view
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		Log.d(TAG, "onFinishInflate: ");
		oriDraView = (ImageView) findViewById(com.eva.me.mysquarescreenlock.R.id.ivOriginDragView);//initialize
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
		if (startPosX == -1 || startPosY == -1) {
			startPosX = oriDraView.getLeft();
			startPosY = oriDraView.getTop();
			Log.d(TAG,"initOnDraw ====>   startPosX: "+ startPosX +"startPosY: " +startPosY );
		}

		int left = 0, top = 0;
		
		//根据究竟是那个方向进行不同的绘制方法
		switch (curDirection) {
		case 0://就在中央位置，所以绘制的坐标就是手指的坐标
			left = dragViewX;
			top = dragViewY;
			break;
			
		case 1://手指这个时候在向上滑动，所以横坐标固定
			left = startPosX;
			top = dragViewY;
			break;
			
		case 2://手指向向右滑动，所以纵坐标不变
			left = dragViewX;
			top = startPosY;
			break;
			
		case 3://手指向下滑动，所以横坐标不变
			left = startPosX;
			top = dragViewY;
			break;
			
		case 4://手指向左滑动，所以这个时候纵坐标不变
			left = dragViewX;
			top = startPosY;
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
		case MotionEvent.ACTION_DOWN://这里是为了监听起点，起点不正确就是不可能会触发最后的onFling事件
			Log.v(TAG, "onTouchEvent: ACTION_DOWN");
			//initialize pos
			dragViewX = (int) event.getX();
			dragViewY = (int) event.getY();
			
			if (handleActionDown(event)) {
				return gestureDetector.onTouchEvent(event);
			}else {
				return false;
			}
//		case MotionEvent.ACTION_MOVE:
//			Log.v(TAG, "onTouchEvent: ACTION_MOVE");
//			//update pos
//			dragViewX = (int) event.getX();
//			dragViewY = (int) event.getY();
//			
//			handleActionMove(event);
//			return true;
//		
//		case MotionEvent.ACTION_UP:
//			handleActionUp();
//			return true;
//			
		default:
			break;
		}
//		return super.onTouchEvent(event);
		Log.d(TAG, "FlingRelativeLayout: onTouch");
		return gestureDetector.onTouchEvent(event);
	}

	/**
	 * @deprecated
	 * @param event
	 */
	private void handleActionUp() {
		// TODO Auto-generated method stub
		resetToInit();
	}

	/**
	 * @deprecated
	 * @param event
	 */
	//control action move event
	private void handleActionMove(MotionEvent event) {
		curDirection = CoordinatesUtil.getDirection(dragViewX, dragViewY);//get direction
		Log.e(TAG, "handleActionMove: curDirection: "+curDirection);
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
		
		Log.e(TAG, "handleActionDown:  isInRect: "+isInRect);
		return isInRect;
	}

	//reset everything to defalut
	private void resetToInit() {
		//reset to default pos
		dragViewX = initDragViewPos;
		dragViewY = initDragViewPos;
		
		oriDraView.setVisibility(View.VISIBLE);
		
		curDirection = 0;//让此时的坐标恢复到最初始的情况下
		
		invalidate();
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case FlingRelativeLayout.GET_DIRECTION:
				Log.e(TAG, "mHandler -> handleMessage -> GET_DIRECTION : ");
				curDirection = LocalOnGestureListener.detectDirection;
				Log.e(TAG, "curDirection: "+curDirection);
				invalidate();//每次收到这种事件的时候就会更新UI
				break;

			default:
				break;
			}
		};
	};
	
}
