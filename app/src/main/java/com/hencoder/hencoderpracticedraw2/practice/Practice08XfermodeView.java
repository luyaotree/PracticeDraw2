package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw2.R;

public class Practice08XfermodeView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap1;
    Bitmap bitmap2;
    PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC);
    PorterDuffXfermode porterDuffXfermode2 = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    PorterDuffXfermode porterDuffXfermode3 = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);

    public Practice08XfermodeView(Context context) {
        super(context);
    }

    public Practice08XfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice08XfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.batman_logo);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 paint.setXfermode() 设置不同的结合绘制效果

        // 别忘了用 canvas.saveLayer() 开启 off-screen buffer

        // 第一个：PorterDuff.Mode.SRC
        int saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(bitmap1, 0, 0, paint);
        paint.setXfermode(porterDuffXfermode);
        canvas.drawBitmap(bitmap2, 0, 0, paint);  // 第一个参数的src，后面的paint是dest
        paint.setXfermode(null);   // 用完及时清除 Xfermode
        canvas.restoreToCount(saved);

        // 第二个：PorterDuff.Mode.DST_IN
        int saved2 = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(bitmap1, bitmap1.getWidth() + 100, 0, paint);
        paint.setXfermode(porterDuffXfermode2);
        canvas.drawBitmap(bitmap2, bitmap1.getWidth() + 100, 0, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(saved2);

        // 第三个：PorterDuff.Mode.DST_OUT
        int saved3 = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(bitmap1, 0, bitmap1.getHeight() + 20, paint);
        paint.setXfermode(porterDuffXfermode3);
        canvas.drawBitmap(bitmap2, 0, bitmap2.getHeight() + 20, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(saved3);

        // 用完之后使用 canvas.restore() 恢复 off-screen buffer
    }
}
