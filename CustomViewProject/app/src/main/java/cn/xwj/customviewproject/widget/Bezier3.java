package cn.xwj.customviewproject.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author: xw
 * Date: 2018-03-08 17:20:05
 * Description: Bezier <this is description>.
 */

public class Bezier3 extends View {
    private static final float C = 0.551915024494f;     // 一个常量，用来计算绘制圆形贝塞尔曲线控制点的位置

    private Paint mPaint;
    private int mCenterX, mCenterY;

    private float mRadius = 200;                  // 圆的半径
    private float mDifference = mRadius * C;        // 圆形的控制点与数据点的差值

    private float[] mDatas = new float[8];               // 顺时针记录绘制圆形的四个数据点
    private float[] mCtrl = new float[16];              // 顺时针记录绘制圆形的八个控制点

    private float mDuration = 1000;                     // 变化总时长
    private float mCurrent = 0;                         // 当前已进行时长
    private float mCount = 100;                         // 将时长总共划分多少份
    private float mPiece = mDuration / mCount;            // 每一份的时长


    public Bezier3(Context context) {
        this(context, null);
    }

    public Bezier3(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Bezier3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);

        //数据点
        mDatas[0] = 0;
        mDatas[1] = mRadius;

        mDatas[2] = mRadius;
        mDatas[3] = 0;

        mDatas[4] = 0;
        mDatas[5] = -mRadius;

        mDatas[6] = -mRadius;
        mDatas[7] = 0;

        //控制点

        mCtrl[0] = mDatas[0] + mDifference;
        mCtrl[1] = mDatas[1];

        mCtrl[2] = mDatas[2];
        mCtrl[3] = mDatas[3] + mDifference;

        mCtrl[4] = mDatas[2];
        mCtrl[5] = mDatas[3] - mDifference;

        mCtrl[6] = mDatas[4] + mDifference;
        mCtrl[7] = mDatas[5];

        mCtrl[8] = mDatas[4] - mDifference;
        mCtrl[9] = mDatas[5];

        mCtrl[10] = mDatas[6];
        mCtrl[11] = mDatas[7] - mDifference;

        mCtrl[12] = mDatas[6];
        mCtrl[13] = mDatas[7] + mDifference;

        mCtrl[14] = mDatas[0] - mDifference;
        mCtrl[15] = mDatas[1];


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCoordinateSystem(canvas);       // 绘制坐标系

        canvas.translate(mCenterX, mCenterY); // 将坐标系移动到画布中央
        canvas.scale(1, -1);                 // 翻转Y轴

        drawAuxiliaryLine(canvas);


//        // 绘制贝塞尔曲线
//        mPaint.setColor(Color.RED);
//        mPaint.setStrokeWidth(8);
//
//        Path path = new Path();
//        path.moveTo(mDatas[0], mDatas[1]);
//
//        path.cubicTo(mCtrl[0], mCtrl[1], mCtrl[2], mCtrl[3], mDatas[2], mDatas[3]);
//        path.cubicTo(mCtrl[4], mCtrl[5], mCtrl[6], mCtrl[7], mDatas[4], mDatas[5]);
//        path.cubicTo(mCtrl[8], mCtrl[9], mCtrl[10], mCtrl[11], mDatas[6], mDatas[7]);
//        path.cubicTo(mCtrl[12], mCtrl[13], mCtrl[14], mCtrl[15], mDatas[0], mDatas[1]);
//
//        canvas.drawPath(path, mPaint);
//
//        mCurrent += mPiece;
//        if (mCurrent < mDuration) {
//
//            mDatas[1] -= 120 / mCount;
//            mCtrl[7] += 80 / mCount;
//            mCtrl[9] += 80 / mCount;
//
//            mCtrl[4] -= 20 / mCount;
//            mCtrl[10] += 20 / mCount;
//
//            postInvalidateDelayed((long) mPiece);
//        }
    }

    // 绘制辅助线
    private void drawAuxiliaryLine(Canvas canvas) {
        // 绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);

        for (int i = 0; i < mDatas.length; i += 2) {
            canvas.drawPoint(mDatas[i], mDatas[i + 1], mPaint);
        }

        for (int i = 0; i < mCtrl.length; i += 2) {
            canvas.drawPoint(mCtrl[i], mCtrl[i + 1], mPaint);
        }

        // 绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(mDatas[2], mDatas[2 + 1], mCtrl[2], mCtrl[2 + 1], mPaint);
        canvas.drawLine(mDatas[2], mDatas[2+1], mCtrl[4], mCtrl[5], mPaint);
//        for (int i = 0, j = 0; i < 8; i += 2, j += 2) {
//            canvas.drawLine(mDatas[i], mDatas[i + 1], mCtrl[j], mCtrl[j + 1], mPaint);
////            canvas.drawLine(mDatas[i], mDatas[i + 1], mCtrl[j], mCtrl[j + 1], mPaint);
//        }

    }

    // 绘制坐标系
    private void drawCoordinateSystem(Canvas canvas) {
        canvas.save();

        canvas.translate(mCenterX, mCenterY);
        canvas.scale(1, -1);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(8);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawLine(-mCenterX * 0.9f, 0, mCenterX * 0.9f, 0, paint);
        canvas.drawLine(0, -mCenterY * 0.9f, 0, mCenterY * 0.9f, paint);
        canvas.restore();
    }
}
