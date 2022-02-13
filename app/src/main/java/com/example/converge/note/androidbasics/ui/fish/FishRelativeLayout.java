package com.example.converge.note.androidbasics.ui.fish;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

public class FishRelativeLayout extends RelativeLayout {


    private int alpha;
    private float radius = 1;
    private FishDrawable fishDrawable;
    private ImageView imageView;
    private Paint mPaint;

    private float touchX;
    private float touchY;


    public FishRelativeLayout(Context context) {
        super(context);
        init();
    }

    public FishRelativeLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FishRelativeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        setWillNotDraw(false);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        imageView = new ImageView(getContext());

        LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(layoutParams);
        fishDrawable = new FishDrawable();
        imageView.setImageDrawable(fishDrawable);
        addView(imageView);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchX = event.getX();
        touchY = event.getY();
        // 水波纹的引擎
        ObjectAnimator rippleAnimator = ObjectAnimator.ofFloat(this, "radius", 0f, 1f).setDuration(1000);
        rippleAnimator.start();
        makeTrail();
        return super.onTouchEvent(event);
    }

    private void makeTrail() {
        //鱼的重心：相对ImageView坐标
        PointF fishRelativeMiddle = fishDrawable.getMiddlePoint();
        //鱼的重心
        PointF fishMiddle = new PointF(imageView.getX() + fishRelativeMiddle.x, imageView.getY() + fishRelativeMiddle.y);
        //鱼头圆心
        PointF fishHead = new PointF(imageView.getX() + fishDrawable.getHeadPoint().x, imageView.getY() + fishDrawable.getHeadPoint().y);
        //终点坐标
        PointF touch = new PointF(touchX, touchY);
        // 计算控制点 控制点角度
        final float angle = includedAngle(fishMiddle, fishHead, touch) / 2;
        float delta = calcultatAngle(fishMiddle, fishHead);
        PointF controlPoint = FishDrawable.calculatePoint(fishMiddle,1.6f * FishDrawable.getHeadRadius(), angle + delta);

        // 鱼游动路径
        Path path = new Path();
        // 移动鱼到七点坐标
        path.moveTo(fishMiddle.x - fishRelativeMiddle.x, fishMiddle.y - fishRelativeMiddle.y);
        // 三阶贝塞尔曲线
        path.cubicTo(fishHead.x - fishRelativeMiddle.x, fishHead.y - fishRelativeMiddle.y,
                controlPoint.x - fishRelativeMiddle.x, controlPoint.y - fishRelativeMiddle.y,
                touch.x - fishRelativeMiddle.x, touch.y - fishRelativeMiddle.y);

        //鱼尾加速摇摆
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "x", "y", path);
        animator.setDuration(2000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                fishDrawable.setFrequence(1f);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                fishDrawable.setFrequence(2f);
            }
        });

        //鱼头转向
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float[] tan = new float[2];
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                pathMeasure.getPosTan(pathMeasure.getLength() * fraction, null, tan);
                // y轴与实际坐标相反，tan[1] 需要取反
                float angle = (float) (Math.toDegrees(Math.atan2(-tan[1], tan[0])));
                fishDrawable.setFishStartAngle(angle);
            }
        });
        animator.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制水波纹
        mPaint.setAlpha(alpha);
        canvas.drawCircle(touchX, touchY, radius * 120 + 20, mPaint);
        invalidate();
    }


    public void setRadius(float radius) {
        // 水波纹的透明度：从100到0
        alpha = (int) (100 * (1 - radius));
        // 水波纹的半径：从0到1变化
        this.radius = radius;
    }

    /**
     * 开始点与结束点连成的线和x轴夹角
     */
    public static float calcultatAngle(PointF start, PointF end) {
        return includedAngle(start, new PointF(start.x + 1, start.y), end);
    }

    /**
     * 利用向量的夹角公式计算夹角
     * cosAOB = (OA*OB)/(|OA|*|OB|)
     * 其中OA*OB是向量的数量积OA=(Ax-Ox,Ay-Oy) OB=(Bx-Ox,By-Oy),OA*OB=(Ax-Ox)*(Bx-Ox)+
     * (Ay-Oy)*(By-Oy)
     *
     * @param center 顶点 O
     * @param head   点1 A
     * @param touch  点2 B
     * @return
     */
    public static float includedAngle(PointF center, PointF head, PointF touch) {
        // OA*OB=(Ax-Ox)*(Bx-Ox)+(Ay-Oy)*(By-Oy)
        float AOB = (head.x - center.x) * (touch.x - center.x) + (head.y - center.y)
                * (touch.y - center.y);
        // OA 的长度
        float OALength = (float) Math.sqrt((head.x - center.x) * (head.x - center.x)
                + (head.y - center.y) * (head.y - center.y));
        // OB 的长度
        float OBLength = (float) Math.sqrt((touch.x - center.x) * (touch.x -
                center.x) + (touch.y - center.y) * (touch.y - center.y));
        // cosAOB = (OA*OB)/(|OA|*|OB|)
        float angleCos = AOB / (OALength * OBLength);
        float temAngle = (float) Math.toDegrees(Math.acos(angleCos));
        //判断方向 正左侧 负右侧 0线上,但是Android的坐标系Y是朝下的，所以左右颠倒一下
        float direction = (center.x - touch.x) * (head.y - touch.y) - (center.y -
                touch.y) * (head.x - touch.x);
        if (direction == 0) {
            if (AOB >= 0) {
                return 0;
            } else
                return 180;
        } else {
            if (direction > 0) {//右侧顺时针为负
                return -temAngle;
            } else {
                return temAngle;
            }
        }
    }
}
