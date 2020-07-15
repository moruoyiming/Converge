package com.example.hotfix.note.class17.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatTextView;

public class CustomTextView extends AppCompatTextView {

    private String text = "波多野结衣";

    private Paint paint;

    private float percent = 0.0f;

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
        invalidate();
    }

    public CustomTextView(Context context) {
        super(context);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        paint = new Paint();
        paint.setTextSize(48);
        paint.setAntiAlias(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        paint.setColor(Color.BLUE);
        Paint.FontMetrics metrics = paint.getFontMetrics();

        canvas.drawText(text, 0, metrics.descent - metrics.ascent, paint);

        canvas.drawText(text, getWidth() / 2, 100, paint);

        int y = (int) (getHeight() / 2 + (metrics.descent - metrics.ascent) / 2) - (int) metrics.descent;
        //画文字的上边线
        paint.setColor(Color.BLUE);
        canvas.drawLine(0, y + metrics.ascent, getWidth(), y + metrics.ascent, paint);
        //画文字的下边线
        paint.setColor(Color.RED);
        canvas.drawLine(0, y + metrics.descent, getWidth(), y + metrics.descent, paint);
        //画文字的控件顶部top线
        paint.setColor(Color.RED);
        canvas.drawLine(0, y + metrics.top, getWidth(), y + metrics.top, paint);
        //画文字的控件底部bottom线
        paint.setColor(Color.RED);
        canvas.drawLine(0, y + metrics.bottom, getWidth(), y + metrics.bottom, paint);
        //top =-50.695312,bottom =13.0078125,ascent=-44.53125,descent=11.71875
        Log.i("xxxxx", " top =" + metrics.top + ",bottom =" + metrics.bottom + ",ascent=" + metrics.ascent + ",descent=" + metrics.descent);
        drawXCenterLine(canvas);
        drawYCenterLine(canvas);
        drawCenterText(canvas);
        drawCenterText2(canvas);
    }

    private void drawCenterText(Canvas canvas) {
        canvas.save();
        float textwidth = paint.measureText(text);
        paint.setColor(Color.BLACK);
        Paint.FontMetrics metrics = paint.getFontMetrics();
        // 横坐标

        int x = (int) (getWidth() / 2 - textwidth / 2);
        float left_x = x + textwidth * percent;
//        int y = (int) getHeight() / 2;//
//        int y = (int) (getHeight() / 2 + (metrics.descent - metrics.ascent) / 2);
        // 纵坐标
        int y = (int) (getHeight() / 2 + (metrics.descent - metrics.ascent) / 2) - (int) metrics.descent;
        //画文字
        canvas.clipRect(left_x, 0, getWidth(), getHeight());
        canvas.drawText(text, x, y, paint);
        canvas.restore();
    }

    private void drawCenterText2(Canvas canvas) {
        canvas.save();
        float textwidth = paint.measureText(text);
        paint.setColor(Color.RED);
        Paint.FontMetrics metrics = paint.getFontMetrics();
        // 横坐标
        float left = getWidth() / 2 - textwidth / 2;
        float right = left + textwidth * percent;
        // 纵坐标
        float baseline = (getHeight() / 2 + (metrics.descent - metrics.ascent) / 2) - (int) metrics.descent;
        //画文字
        canvas.clipRect(left, 0, right, getHeight());
        canvas.drawText(text, left, baseline, paint);
        canvas.restore();
    }

    private void drawXCenterLine(Canvas canvas) {
        canvas.save();
        paint.setColor(Color.RED);
        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), paint);
        canvas.restore();
    }

    private void drawYCenterLine(Canvas canvas) {
        canvas.save();
        paint.setColor(Color.GREEN);
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, paint);
        canvas.restore();
    }


}
