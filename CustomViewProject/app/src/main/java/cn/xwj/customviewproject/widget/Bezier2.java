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

public class Bezier2 extends View {
    private Paint mPaint;
    private Point mStartPoint, mEndPoint, mControlPoint1, mControlPoint2;
    private int mCenterX, mCenterY;
    private Path mPath;
    private boolean mode = true;


    public Bezier2(Context context) {
        this(context, null);
    }

    public Bezier2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Bezier2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        mControlPoint1 = new Point(0, 0);
        mControlPoint2 = new Point(0, 0);

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
        mControlPoint1.x = mCenterX;
        mControlPoint1.y = mCenterY - 100;
        mControlPoint2.x = mCenterX +100;
        mControlPoint2.y = mCenterY -100;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mode) {
            mControlPoint1.x = (int) event.getX();
            mControlPoint1.y = (int) event.getY();
        } else {
            mControlPoint2.x = (int) event.getX();
            mControlPoint2.y = (int) event.getY();
        }

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
        canvas.drawPoint(mControlPoint1.x, mControlPoint1.y, mPaint);
        canvas.drawPoint(mControlPoint2.x, mControlPoint2.y, mPaint);

        mPaint.setStrokeWidth(4);
        canvas.drawLine(mStartPoint.x, mStartPoint.y, mControlPoint1.x, mControlPoint1.y, mPaint);
        canvas.drawLine(mControlPoint1.x, mControlPoint1.y, mControlPoint2.x, mControlPoint2.y, mPaint);
        canvas.drawLine(mEndPoint.x, mEndPoint.y, mControlPoint2.x, mControlPoint2.y, mPaint);


        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);
        mPath.reset();
        mPath.moveTo(mStartPoint.x, mStartPoint.y);
        mPath.cubicTo(mControlPoint1.x, mControlPoint1.y,mControlPoint2.x, mControlPoint2.y, mEndPoint.x, mEndPoint.y);
        canvas.drawPath(mPath, mPaint);
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }
}
