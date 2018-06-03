package cn.xwj.kotlinmail.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageView
import cn.xwj.kotlinmail.R
import org.jetbrains.anko.dimen
import java.security.ProtectionDomain

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-02 2018/6/2
 * Description: RoundRectImageView
 */
class RoundRectImageView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {
    val radius = dimen(R.dimen.common_radius).toFloat()
    //设置圆角为左上和右上
    private val radiusArray: FloatArray = floatArrayOf(radius, radius, radius, radius, 0.0f, 0.0f, 0.0f, 0.0f)


    override fun draw(canvas: Canvas) {

        var bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        var localCanvas = Canvas(bitmap)
        if (bitmap.isRecycled) {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            localCanvas = Canvas(bitmap)
        }
        super.draw(localCanvas)
        drawRoundAngle(localCanvas)
        val paint = Paint()
        paint.xfermode = null
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint)
        bitmap.recycle()
    }

    private fun drawRoundAngle(canvas: Canvas) {
        val paint = Paint()
        paint.isAntiAlias = true
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        val path = Path()
        path.addRoundRect(RectF(0f, 0f, width.toFloat(),
                height.toFloat()), this.radiusArray, Path.Direction.CW)
        path.fillType = Path.FillType.INVERSE_WINDING
        canvas.drawPath(path, paint)
    }
}