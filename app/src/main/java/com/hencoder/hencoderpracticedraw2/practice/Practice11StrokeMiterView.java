package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice11StrokeMiterView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();

    public Practice11StrokeMiterView(Context context) {
        super(context);
    }

    public Practice11StrokeMiterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11StrokeMiterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStrokeWidth(40);
        paint.setStyle(Paint.Style.STROKE);

        path.rLineTo(200, 0);
        path.rLineTo(-160, 120);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();

        canvas.translate(100, 100);
        // MITER 值：1, 由于画的角度的比值会大于1，所以会自动调整为Paint.Join.BEVEL
        paint.setStrokeMiter(1);
        canvas.drawPath(path, paint);

        canvas.translate(300, 0);
        // MITER 值：2, 由于画的角度的比值会大于2，所以会自动调整为Paint.Join.BEVEL
        paint.setStrokeMiter(2);
        canvas.drawPath(path, paint);

        canvas.translate(300, 0);
        // MITER 值：5, 由于画的角度的比值小于5，所以不会调整为Paint.Join.BEVEL，就会尖尖的
        paint.setStrokeMiter(5);
        canvas.drawPath(path, paint);

        canvas.restore();
    }
}
