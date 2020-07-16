package com.example.hotfix.note.class18;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class MyImageView extends LinearLayout {

    Paint mPaint;
    private float radius = 1;

    public MyImageView(Context context) {
        super(context);
        init();
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
    }


    float x;
    float y;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        // 水波纹的引擎
        ObjectAnimator rippleAnimator = ObjectAnimator.ofFloat(this, "radius", 0f, 1f).setDuration(1000);
        rippleAnimator.start();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制水波纹
        mPaint.setAlpha(alpha);
        canvas.drawCircle(x, y, radius * 100 + 20, mPaint);
        invalidate();
    }

    int alpha;

    public void setRadius(float radius) {
        // 水波纹的透明度：从100到0
        alpha = (int) (100 * (1 - radius));
        // 水波纹的半径：从0到1变化
        this.radius = radius;
    }
}
