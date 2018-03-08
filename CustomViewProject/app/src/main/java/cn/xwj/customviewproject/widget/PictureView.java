package cn.xwj.customviewproject.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.drawable.PictureDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author: xw
 * Date: 2018-03-02 11:42:49
 * Description: PictureView <this is description>.
 */

public class PictureView extends View{

    private Picture mPicture = new Picture();

    public PictureView(Context context) {
        super(context);
    }

    public PictureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PictureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void recording(){
        Canvas canvas = mPicture.beginRecording(500, 500);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        canvas.translate(200, 250);
        canvas.drawCircle(0,0,100, paint);
        mPicture.endRecording();

        canvas.drawPicture(mPicture);

        PictureDrawable pictureDrawable = new PictureDrawable(mPicture);
        pictureDrawable.setBounds(0,0, 250, mPicture.getHeight());
        pictureDrawable.draw(canvas);


    }
}
