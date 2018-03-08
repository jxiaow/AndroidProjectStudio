package cn.xwj.customviewproject.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cn.xwj.customviewproject.R;

/**
 * Author: xw
 * Date: 2018-03-02 12:31:38
 * Description: CheckView <this is description>.
 */

public class CheckView extends View {

    private static final int CHECK_ANIM = 0;
    private static final int UN_CHECK_ANIM = 1;

    private int mWidth = 0;
    private int mHeight = 0;
    private Bitmap mBitmap;
    private Paint mPaint;
    private Rect mSrcRect;
    private Rect mDstRect;

    private int totalPage = 13;
    private int currentPage = -1;


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CHECK_ANIM:
                    currentPage++;
                    if (currentPage > totalPage -1 ) {
                        mHandler.removeMessages(CHECK_ANIM);
                        return;
                    }
                    invalidate();
                    mHandler.sendEmptyMessageDelayed(CHECK_ANIM, 500 / totalPage);
                    break;
                case UN_CHECK_ANIM:
                    currentPage--;
                    if(currentPage < -1){
                        mHandler.removeMessages(UN_CHECK_ANIM);
                        return;
                    }
                    invalidate();
                    mHandler.sendEmptyMessageDelayed(UN_CHECK_ANIM, 500 / totalPage);
                    break;
            }
        }
    };

    public CheckView(Context context) {
        this(context, null);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_checkmark);
        mPaint = new Paint();
        mPaint.setColor(0xffFF5317);
        mPaint.setStyle(Paint.Style.FILL);

        mSrcRect = new Rect();
        mDstRect = new Rect();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawCircle(0, 0, 240, mPaint);

        int slideLength = mBitmap.getWidth();
        mSrcRect.set(slideLength * currentPage / totalPage, 0,
                slideLength * (currentPage + 1) / totalPage, mBitmap.getHeight());
        mDstRect.set(-200, -200, 200, 200);
        canvas.drawBitmap(mBitmap, mSrcRect, mDstRect, null);
    }

    public void startCheck() {
        mHandler.sendEmptyMessageDelayed(CHECK_ANIM, 500);
    }

    public void startUnCheck() {
        mHandler.sendEmptyMessageDelayed(UN_CHECK_ANIM, 500);
    }
}
