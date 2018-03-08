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
 * Date: 2018-03-07 16:56:51
 * Description: PathView <this is description>.
 */

public class PathView extends View {

    private int mWidth;
    private int mHeight;
    private int mCount = 6; //数据的个数，代表着几边形
    private float mRadius;
    private float mRad = (float) (Math.PI * 2 / mCount);
    private Paint mMainPaint;
    private Paint mTextPaint;
    private Paint mValuePaint;
    private char[] mTextArray = {'a', 'b', 'c', 'd', 'e', 'f'};
    private double[] data = {100, 60, 60, 60, 100, 50, 10, 20}; //各维度分值
    private float maxValue = 100;             //数据最大值

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mMainPaint = new Paint();
        mMainPaint.setColor(Color.GRAY);
        mMainPaint.setStyle(Paint.Style.STROKE);
        mMainPaint.setAntiAlias(true);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextSize(20);

        mValuePaint = new Paint();
        mValuePaint.setColor(Color.BLUE);
        mValuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mValuePaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mRadius = Math.min(w, h) / 2 * 0.9f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        drawPolygon(canvas);
        drawText(canvas);
        drawLines(canvas);
        drawValues(canvas);
    }

    private void drawValues(Canvas canvas) {
        Path path = new Path();
        mValuePaint.setAlpha(255);
        for (int i = 0; i < mCount; i++) {
            float percent = (float) (data[i] / maxValue);
            float x = (float) (mRadius * Math.cos(mRad * i) * percent);
            float y = (float) (mRadius * Math.sin(mRad * i) * percent);
            if (i == 0) {
                path.moveTo(x, 0);
            } else {
                path.lineTo(x, y);
            }
            canvas.drawCircle(x, y, 10, mValuePaint);
        }
        mValuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, mValuePaint);
        mValuePaint.setAlpha(127);
        mValuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, mValuePaint);
    }

    /**
     * 画线
     *
     * @param canvas
     */
    private void drawLines(Canvas canvas) {
        for (int i = 0; i < mCount; i++) {
            float x = (float) (mRadius * Math.cos(mRad * i));
            float y = (float) (mRadius * Math.sin(mRad * i));
            canvas.drawLine(0, 0, x, y, mMainPaint);
        }
    }

    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;

        for (int i = 0; i < mCount; i++) {
            float x = (float) ((mRadius + fontHeight / 2) * Math.cos(mRad * i));
            float y = (float) ((mRadius + fontHeight / 2) * Math.sin(mRad * i));
            float rad = mRad * i;

            if (rad > 0 && rad <= Math.PI / 2) {
                float dis = mTextPaint.measureText(String.valueOf(mTextArray[i]));
                canvas.drawText(mTextArray, i, 1, x + dis, y, mTextPaint);
            } else if (rad > Math.PI / 2 && rad <= 3 * Math.PI / 2) {
                float dis = mTextPaint.measureText(String.valueOf(mTextArray[i]));
                canvas.drawText(mTextArray, i, 1, x - dis, y, mTextPaint);
            } else {
                canvas.drawText(mTextArray, i, 1, x, y, mTextPaint);
            }
        }
    }

    /**
     * 画多边形
     *
     * @param canvas
     */
    private void drawPolygon(Canvas canvas) {

        Path path = new Path();
        float r = mRadius / (mCount - 1);

        for (int i = 1; i < mCount; i++) {
            path.reset();
            float curR = r * i;

            for (int j = 0; j < mCount; j++) {
                if (j == 0) {
                    path.moveTo(curR, 0);
                } else {
                    float x = (float) (curR * Math.cos(mRad * j));
                    float y = (float) (curR * Math.sin(mRad * j));
                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path, mMainPaint);
        }
    }
}
