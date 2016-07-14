package com.example.dllo.a36kr.main.tools;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.dllo.a36kr.R;

/**
 * Created by dllo on 16/6/25.
 */
public class RoundImage extends ImageView {
    private boolean isRound = false;

    public RoundImage(Context context) {
        super(context);
    }

    public RoundImage(Context context, AttributeSet attrs) {

        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundImage);
        isRound = typedArray.getBoolean(R.styleable.RoundImage_is_round, false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isRound) {
            //需要是显示的是圆形图片
            BitmapDrawable drawable = (BitmapDrawable) getDrawable();
            if (drawable != null) {
                //ImageView  设置了图片属性
                //从它里面拿到设置的那张图片
                Bitmap bitmap = drawable.getBitmap();
                Bitmap out = getCircleBitmap(bitmap);
                Paint paint = new Paint();

                Rect rect = new Rect(0, 0, out.getWidth(), out.getHeight());
                canvas.drawBitmap(out, rect, rect, paint);
            }
        } else {
            super.onDraw(canvas);
        }
    }

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        //生成一张空的和目标Bitmap  宽高相同的bitmap
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //新建一个画布
        Canvas canvas = new Canvas(outBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        //绘制底层的圆
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);
        //设置叠放风格,为 SRC_IN 显示前景的交集部分
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //在其上面绘制一张bitmap
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        //从传进来的bitmap 里拿到矩形范围
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return outBitmap;
    }
}
