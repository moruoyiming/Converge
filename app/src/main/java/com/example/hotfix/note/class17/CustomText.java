package com.example.hotfix.note.class17;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class CustomText extends AppCompatTextView {

    private String text = "波多野结衣";

    private Paint paint;

    public CustomText(Context context) {
        super(context);
        init();
    }

    public CustomText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomText(Context context, AttributeSet attrs, int defStyleAttr) {
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

        canvas.drawText(text, 0, metrics.descent-metrics.ascent, paint);

        canvas.drawText(text, getWidth() / 2, 100, paint);

        drawXCenterLine(canvas);
        drawYCenterLine(canvas);
        drawCenterText(canvas);

    }

    private void drawCenterText(Canvas canvas) {
        float textwidth = paint.measureText(text);
        Paint.FontMetrics metrics = paint.getFontMetrics();
        // 横坐标
        int x = (int) (getWidth() / 2 - textwidth / 2);
//        int y = (int) getHeight() / 2;//
//        int y = (int) (getHeight() / 2 + (metrics.descent - metrics.ascent) / 2);
        // 纵坐标
        int y = (int) (getHeight() / 2 + (metrics.descent - metrics.ascent) / 2)-(int)metrics.descent;
        //画文字
        canvas.drawText(text, x, y, paint);
        //画文字的上边线
        paint.setColor(Color.BLUE);
        canvas.drawLine(0, y +metrics.ascent, getWidth(), y +metrics.ascent, paint);
        //画文字的下边线
        paint.setColor(Color.RED);
        canvas.drawLine(0, y +metrics.descent, getWidth(), y +metrics.descent, paint);
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
