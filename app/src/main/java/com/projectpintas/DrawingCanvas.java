package com.projectpintas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class DrawingCanvas extends View {

	private Paint mPaint;
	private Path mPath;
	private Rect mDrawRect;
	private Canvas mCanvas;

	// Bitmap to store different backgrounds
	private Bitmap bitmap;
	private Bitmap mBitmap;
	private Bitmap tBitmap;
	private Bitmap mtBitmap;

	// Default width and height of view
	private final int mDrawBitmapWidth = 320;
	private final int mDrawBitmapHeight = 480;
	private int width;
	private int height;
	private float startX;
	private float startY;
	private float endX;
	private float endY;
	private long startTime;
	private long endTime;

	// Static variables to allow background to be changed
	static boolean dots;
	static int canvas_choice = 0;

	// Constructors
	public DrawingCanvas(Context context, AttributeSet aSet) {
		super(context, aSet);
		init();

	}

	public DrawingCanvas(Context context) {
		super(context);
		init();
	}

	public DrawingCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {

		// Reset background to full letter shape
		dots = false;

		// Create a bitmap with the background image
		bitmap = BitmapFactory
				.decodeResource(getResources(), R.drawable.pintas_no_name_faded_canvas);
		// Makes the bitmap mutable
		mBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);

		// Create a bitmap with the letter to be drawn based on value of static
		// variable

		switch (canvas_choice) {

		case 1:
			tBitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.letters_xhdpi_s);
			mtBitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.dots_xhdpi_s);
			break;

		case 2:
			tBitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.letters_xhdpi_a);
			mtBitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.dots_xhdpi_a);
			break;

		case 3:
			tBitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.letters_xhdpi_t);
			mtBitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.dots_xhdpi_t);
			break;

		case 4:
			tBitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.letters_xhdpi_i);
			mtBitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.dots_xhdpi_i);
			break;

		case 5:
			tBitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.letters_xhdpi_p);
			mtBitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.dots_xhdpi_p);
			break;

		case 6:
			tBitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.letters_xhdpi_n);
			mtBitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.dots_xhdpi_n);
			break;
		default:
			tBitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.pintas_no_name_faded_canvas);
			Toast.makeText(getContext(), "Error...",
					Toast.LENGTH_LONG).show();
			break;
		}

		// Set canvas to background bitmap and path for connecting points on canvas
		mCanvas = new Canvas(mBitmap);
		mPath = new Path();

		// Set paint attributes
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(Color.rgb(0, 0, 0));
		mPaint.setFilterBitmap(true);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(25);

		//Set up the canvas dimensions
		mDrawRect = new Rect(0, 0, mDrawBitmapWidth, mDrawBitmapHeight);

	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// Clear the canvas
		canvas.drawColor(Color.TRANSPARENT);

		// Draws background
		canvas.drawBitmap(bitmap, null, mDrawRect, mPaint);

		if (dots == true) {

			// Put letter on canvas
			canvas.drawBitmap(mtBitmap, null, mDrawRect, mPaint);

		} else {
			// Draws the letter to canvas
			canvas.drawBitmap(tBitmap, null, mDrawRect, mPaint);
		}

		canvas.drawPath(mPath, mPaint);

	}

	// Determines view size
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		width = height = 0;

		// Determine width
		switch (widthMode) {
		case MeasureSpec.EXACTLY:
			width = widthSize;
			break;
		case MeasureSpec.AT_MOST:
			width = Math.min(mDrawBitmapWidth, widthSize);
			break;
		case MeasureSpec.UNSPECIFIED:
		default:
			width = mDrawBitmapWidth;
			break;
		}

		// Determine height
		switch (heightMode) {
		case MeasureSpec.EXACTLY:
			height = heightSize;
			break;
		case MeasureSpec.AT_MOST:
			height = Math.min(mDrawBitmapHeight, heightSize);
			break;
		case MeasureSpec.UNSPECIFIED:
		default:
			height = mDrawBitmapHeight;
			break;
		}

		setMeasuredDimension(width, height);
	}

	@Override
	// Returns the size of the view and set the size of the drawing the rectangle
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {

		super.onSizeChanged(w, h, oldw, oldh);

		mDrawRect.set(0, 0, w, h);
	}

	private float mX, mY;
	private static final float TOUCH_TOLERANCE = 1;
       //Move to point touched on screen
	private void start_touch(float x, float y) {
		mPath.moveTo(x, y);
		mPath.addCircle(x, y, 1, Path.Direction.CW);
		mX = x;
		mY = y;

	}
    //Draw the line between points x and y
	private void move_touch(float x, float y) {
		float dx = Math.abs(x - mX);
		float dy = Math.abs(y - mY);
		if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
			mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
			mX = x;
			mY = y;
		}

	}

	private void up_touch() {
		mPath.lineTo(mX, mY);
		mCanvas.drawPath(mPath, mPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int maskedAction = event.getActionMasked();
		float x = event.getX();
		float y = event.getY();

		switch (maskedAction) {

		case MotionEvent.ACTION_DOWN:
			startTime  = ~System.currentTimeMillis();
			start_touch(x, y);
			startX = x;
			startY = y;
			invalidate();
			break;

		case MotionEvent.ACTION_MOVE:
			move_touch(x, y);
			invalidate();
			break;

		case MotionEvent.ACTION_UP:
			long endTime = System.currentTimeMillis();
			endX = x;
			endY = y;
			up_touch();
			invalidate();
			break;
		}

		return true;

	}
	// Redraw the screen
	public void clear() {
		mPath.reset();
		invalidate();
	}
}
