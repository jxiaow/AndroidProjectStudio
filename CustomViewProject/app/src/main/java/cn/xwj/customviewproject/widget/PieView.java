package cn.xwj.customviewproject.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import cn.xwj.customviewproject.entity.Pie;

/**
 * Author: xw
 * Date: 2018-02-13 09:41:51
 * Description: PieView <this is description>.
 */

public class PieView extends View {
    // 颜色表(注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000,
            0xFFFF8C69, 0xFF808080, 0xFFE6B800, 0xFF7CFC00};

    private Paint mPaint;
    private Context mContext;
    private int mWidth;
    private int mHeight;
    private List<Pie> mPieList;
    private RectF mRectF;
    private float currentAngle = 0f;


    public void setPieList(List<Pie> pieList) {
        mPieList = pieList;
        initData();
        invalidate();
    }

    private void initData() {
        if (mPieList == null) {
            return;
        }
        float sumValue = 0;
        for (int i = 0; i < mPieList.size(); i++) {
            Pie pie = mPieList.get(i);
            sumValue += pie.getValue();
            pie.setColor(mColors[i % mColors.length]);
        }

        for (int i = 0; i < mPieList.size(); i++) {
            Pie pie = mPieList.get(i);
            pie.setPercent(pie.getValue() / sumValue);
            pie.setAngle(pie.getPercent() * 360);
        }
    }

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
        mRectF = new RectF(-r, -r, r, r);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPieList == null) {
            return;
        }
        //将画布原点移至中心点
        canvas.translate(mWidth / 2, mHeight / 2);
        for (Pie pie : mPieList) {
            mPaint.setColor(pie.getColor());
            canvas.drawArc(mRectF, currentAngle, pie.getAngle(), true, mPaint);
            currentAngle += pie.getAngle();
        }

        //在坐标原点绘制一个黑色圆形
        mPaint.setColor(Color.BLACK);
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 20, mPaint);

        //在坐标原点绘制一个蓝色圆形
        mPaint.setColor(Color.BLUE);
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 20, mPaint);


        canvas.translate(mWidth / 2, mHeight / 2);


        RectF rectF = new RectF(0, -400, 400, 0);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF, mPaint);

        canvas.scale(0.5f, 0.5f);
        canvas.scale(0.5f, 0.5f, 200, 0);

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);




    }
}
