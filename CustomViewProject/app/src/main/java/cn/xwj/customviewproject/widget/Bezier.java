package cn.xwj.customviewproject.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Author: xw
 * Date: 2018-03-08 17:20:05
 * Description: Bezier <this is description>.
 */

public class Bezier extends View {
    private Paint mPaint;
    private Point mStartPoint, mEndPoint, mControlPoint;
    private int mCenterX, mCenterY;
    private Path mPath;


    public Bezier(Context context) {
        this(context, null);
    }

    public Bezier(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Bezier(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);

        mStartPoint = new Point(0, 0);
        mEndPoint = new Point(0, 0);
        mControlPoint = new Point(0, 0);

        mPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;

        mStartPoint.x = mCenterX - 200;
        mStartPoint.y = mCenterY;
        mEndPoint.x = mCenterX + 200;
        mEndPoint.y = mCenterY;
        mControlPoint.x = mCenterX;
        mControlPoint.y = mCenterY - 100;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mControlPoint.x = (int) event.getX();
        mControlPoint.y = (int) event.getY();
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(mStartPoint.x, mStartPoint.y, mPaint);
        canvas.drawPoint(mEndPoint.x, mEndPoint.y, mPaint);
        canvas.drawPoint(mControlPoint.x, mControlPoint.y, mPaint);

        mPaint.setStrokeWidth(4);
        canvas.drawLine(mStartPoint.x, mStartPoint.y, mControlPoint.x, mControlPoint.y, mPaint);
        canvas.drawLine(mEndPoint.x, mEndPoint.y, mControlPoint.x, mControlPoint.y, mPaint);


        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);
        mPath.reset();
        mPath.moveTo(mStartPoint.x, mStartPoint.y);
        mPath.quadTo(mControlPoint.x, mControlPoint.y, mEndPoint.x, mEndPoint.y);
        canvas.drawPath(mPath, mPaint);
    }
}
