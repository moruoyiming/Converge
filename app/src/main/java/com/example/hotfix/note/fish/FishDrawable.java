package com.example.hotfix.note.fish;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FishDrawable extends Drawable {

    private Path mPath;
    private Paint mPaint;
    //转弯更自然的重心(身体的中心点)
    private PointF middlePoint;
    // 透明度
    private static final int OTHER_ALPHA = 110;
    // 鱼身的透明度
    private static final int BODY_ALPHA = 160;
    // 鱼的初始角度,0表示朝x轴正方向，90表示朝y轴负方向
    private float fishStartAngle = 90;
    //鱼头圆的圆心
    private PointF headPoint;
    // ----------鱼的各部位长度----------------
    // 鱼头半径
    private static final float HEAD_RADIUS = 50;
    // 身体的长度
    private static final float BODY_LENGHT = HEAD_RADIUS * 3.2f;

    // ----------鱼鳍----------------
    // 寻找鱼鳍开始点的线长
    private static final float FIND_FINS_LENGTH = HEAD_RADIUS * 0.9f;
    // 鱼鳍的长度
    private final float FINS_LENGTH = HEAD_RADIUS * 1.3f;

    // -------------鱼尾---------------
    // 尾部大圆的半径(圆心就是身体底部的中点)
    private final float BIG_CIRCLE_RADIUS = HEAD_RADIUS * 0.7f;
    // --寻找尾部中圆圆心的线长
    private final float FIND_MIDDLE_CIRCLE_LENGTH = BIG_CIRCLE_RADIUS * (0.6f + 1);
    // 尾部中圆的半径
    private final float MIDDLE_CIRCLE_RADIUS = BIG_CIRCLE_RADIUS * 0.6f;
    // --寻找尾部小圆圆心的线长
    private final float FIND_SMALL_CIRCLE_LENGTH = MIDDLE_CIRCLE_RADIUS * (0.4f + 2.7f);
    // 尾部小圆的半径
    private final float SMALL_CIRCLE_RADIUS = MIDDLE_CIRCLE_RADIUS * 0.4f;
    // --寻找大三角形底边中心点的线长
    private final float FIND_TRIANGLE_LENGTH = FIND_SMALL_CIRCLE_LENGTH;

    private final float EYE_CIRCLE = HEAD_RADIUS * 0.2f;

    private final float CHANGE_VALUE = 3600f;
    // 动画持续时间
    private final int ANIMATOR_DURATION = 8 * 1000;

    private float currentValue;

    private float frequence = 1f;

    public FishDrawable() {
        init();
    }

    private void init() {
        // 路径
        mPath = new Path();
        // 画笔
        mPaint = new Paint();
        // 抗锯齿
        mPaint.setAntiAlias(true);
        // 画笔类型填充
        mPaint.setStyle(Paint.Style.FILL);
        // 防抖
        mPaint.setDither(true);
        // 设置颜色
        mPaint.setColor(Color.argb(OTHER_ALPHA, 244, 92, 71)); // 与Point一样，只是坐标为浮点数
        // 重心位于整个控件的中心，保证鱼旋转的空间
        middlePoint = new PointF(4.18f * HEAD_RADIUS, 4.18f * HEAD_RADIUS);
        // 变化值
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, CHANGE_VALUE);
        // 设置动画周期
        valueAnimator.setDuration(ANIMATOR_DURATION);
        // 设置循环次数，无限次
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        // 设置循环模式
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        // 默认插值器为
        valueAnimator.setInterpolator(new LinearInterpolator());

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                currentValue = (float) animator.getAnimatedValue();
                invalidateSelf();
            }
        });
        valueAnimator.start();
    }

    /**
     * 绘制，类似自定义View中的onDraw方法
     */
    @Override
    public void draw(@NonNull Canvas canvas) {
        makeFish(canvas);
    }


    /**
     * 输入起点、长度、旋转角度计算终点 *
     *
     * @param startPoint 起点
     * @param length     长度
     * @param angle      旋转角度
     * @return 计算结果点
     */
    public static PointF calculatePoint(PointF startPoint, float length, float angle) {
        // Math.toRadians 角度转弧度 --- sin\cos的参数是弧度制
        float deltaX = (float) Math.cos(Math.toRadians(angle)) * length;
        // 符合Android坐标的y轴朝下的标准
        float deltaY = (float) Math.sin(Math.toRadians(angle - 180)) * length;
        return new PointF(startPoint.x + deltaX, startPoint.y + deltaY);
    }

    /**
     * 画鱼
     */
    private void makeFish(Canvas canvas) {
        // 鱼的摆动角度
        float fishAngle = (float) (fishStartAngle + Math.sin(Math.toRadians(currentValue * 1.2 * frequence)) * 8);
        // 画鱼头的圆心坐标
        headPoint = calculatePoint(middlePoint, BODY_LENGHT / 2, fishAngle); // 画鱼头
        //画鱼头
        canvas.drawCircle(headPoint.x, headPoint.y, HEAD_RADIUS, mPaint);
        PointF leftEye = calculatePoint(headPoint, HEAD_RADIUS, fishAngle - 25);
        makeEye(canvas, leftEye, true);
        PointF rightEye = calculatePoint(headPoint, HEAD_RADIUS, fishAngle + 25);
        makeEye(canvas, rightEye, true);

        // 画右鳍的起点坐标
        PointF rightFinsPoint = calculatePoint(headPoint, FIND_FINS_LENGTH, fishAngle - 110);
        // 画右鳍
        makeFins(canvas, rightFinsPoint, fishAngle, true);
        // 画左鳍的起点坐标
        PointF leftFinsPoint = calculatePoint(headPoint, FIND_FINS_LENGTH, fishAngle + 110);
        // 画左鳍
        makeFins(canvas, leftFinsPoint, fishAngle, false);
        // 身体底部的中心点
        PointF bodyBottomCenterPoint = calculatePoint(headPoint, BODY_LENGHT, fishAngle - 180);
        // 节肢1
        PointF tailMainPoint = makeSegment(canvas, bodyBottomCenterPoint, BIG_CIRCLE_RADIUS,
                FIND_MIDDLE_CIRCLE_LENGTH, MIDDLE_CIRCLE_RADIUS, fishAngle, true);
        // 节肢2
        makeSegment(canvas, tailMainPoint, MIDDLE_CIRCLE_RADIUS,
                FIND_SMALL_CIRCLE_LENGTH, SMALL_CIRCLE_RADIUS, fishAngle, false);
        // 尾部三角形
        float triangleHalfLength = (float) Math.abs(Math.sin(Math.toRadians(currentValue * 1.2 * frequence)) * BIG_CIRCLE_RADIUS);
        makeTriangle(canvas, tailMainPoint, FIND_TRIANGLE_LENGTH, triangleHalfLength, fishAngle);
        makeTriangle(canvas, tailMainPoint, FIND_TRIANGLE_LENGTH - 10,
                triangleHalfLength - 20, fishAngle);
        // 鱼身
        makeBody(canvas, bodyBottomCenterPoint, fishAngle);
    }

    private void makeEye(Canvas canvas, PointF leftEye, boolean isLeftEye) {
        //画鱼眼
        canvas.drawCircle(leftEye.x, leftEye.y, EYE_CIRCLE, mPaint);

    }

    /**
     * 画鱼鳍
     *
     * @param canvas
     * @param startPoint
     * @param isRightFins 绘制的是否是右鱼鳍，true是的
     */
    private void makeFins(Canvas canvas, PointF startPoint, float fishAngle, boolean isRightFins) {
        // 鱼鳍二阶贝塞尔曲线的控制点
        float controlAngle = 115;//鱼鳍三角控制角度
        // 鱼鳍结束点
        PointF endPoint = calculatePoint(startPoint, FINS_LENGTH, fishAngle - 180);
        // 控制点
        PointF controlPoint = calculatePoint(startPoint, FINS_LENGTH * 1.8f,
                isRightFins ? fishAngle - controlAngle : fishAngle + controlAngle);
        mPath.reset();
        // 移动到画鱼鳍的起点坐标
        mPath.moveTo(startPoint.x, startPoint.y);
        // 二阶贝塞尔曲线
        mPath.quadTo(controlPoint.x, controlPoint.y, endPoint.x, endPoint.y);
        canvas.drawPath(mPath, mPaint);
    }

    /**
     * 画节肢1,2(这两个摆动的时候是分开的，必须分开画) *
     *
     * @param bottomCenterPoint
     * @param bigCircleRadius
     * @param findSmallCircleLength
     * @param smallCircleRadius
     * @param hasBigCircle
     * @return 计算节肢1的时候需要返回梯形小圆的圆心点，这个是绘制节肢2和三角形的起始点
     */
    private PointF makeSegment(Canvas canvas, PointF bottomCenterPoint, float
            bigCircleRadius, float findSmallCircleLength, float smallCircleRadius,
                               float fishAngle, boolean hasBigCircle) {
        float segmentAngle;
        if (hasBigCircle) {//节肢1
            segmentAngle = (float) (fishAngle + Math.cos(Math.toRadians(currentValue * 1.2 * frequence)) * 8);
        } else {//节肢2
            segmentAngle = (float) (fishAngle + Math.sin(Math.toRadians(currentValue * 1.2 * frequence)) * 8);
        }
        // 梯形上底的中心点
        PointF upperCenterPoint = calculatePoint(bottomCenterPoint, findSmallCircleLength, segmentAngle - 180);
        // 梯形的四个点
        PointF bottomLeftPoint = calculatePoint(bottomCenterPoint, bigCircleRadius, segmentAngle + 90);
        PointF bottomRightPoint = calculatePoint(bottomCenterPoint, bigCircleRadius, segmentAngle - 90);
        PointF upperLeftPoint = calculatePoint(upperCenterPoint, smallCircleRadius, segmentAngle + 90);
        PointF upperRightPoint = calculatePoint(upperCenterPoint, smallCircleRadius, segmentAngle - 90);
        if (hasBigCircle) {
            // 画大圈
            canvas.drawCircle(bottomCenterPoint.x, bottomCenterPoint.y, bigCircleRadius, mPaint);
        }
        // 画小圈
        canvas.drawCircle(upperCenterPoint.x, upperCenterPoint.y, smallCircleRadius, mPaint);
        // 画梯形
        mPath.reset();
        mPath.moveTo(bottomLeftPoint.x, bottomLeftPoint.y);
        mPath.lineTo(upperLeftPoint.x, upperLeftPoint.y);
        mPath.lineTo(upperRightPoint.x, upperRightPoint.y);
        mPath.lineTo(bottomRightPoint.x, bottomRightPoint.y);
        canvas.drawPath(mPath, mPaint);
        return upperCenterPoint;
    }

    /**
     * 绘制三角形
     *
     * @param findTriangleLength 寻找三角形底边中心点的线长 * @param triangleHalfLength 底边一半的长度
     */
    private void makeTriangle(Canvas canvas, PointF startPoint, float
            findTriangleLength, float triangleHalfLength, float fishAngle) {
        float segmentAngle = (float) (fishAngle + Math.sin(Math.toRadians(currentValue * frequence)) * 10);
        // 三角形底边的中心点
        PointF centerPoint = calculatePoint(startPoint, findTriangleLength, segmentAngle - 180);
        // 三角形底边的两点
        PointF leftPoint = calculatePoint(centerPoint, triangleHalfLength, segmentAngle + 90);
        PointF rightPoint = calculatePoint(centerPoint, triangleHalfLength, segmentAngle - 90);
        // 绘制三角形
        mPath.reset();
        mPath.moveTo(startPoint.x, startPoint.y);
        mPath.lineTo(leftPoint.x, leftPoint.y);
        mPath.lineTo(rightPoint.x, rightPoint.y);
        canvas.drawPath(mPath, mPaint);
    }
    // 与节肢2的摆幅一样

    /**
     * 画鱼身体
     */
    private void makeBody(Canvas canvas, PointF bodyBottomCenterPoint, float fishAngle) {
        // 身体的四个点
        PointF topLeftPoint = calculatePoint(headPoint, HEAD_RADIUS, fishAngle + 80);
        PointF topRightPoint = calculatePoint(headPoint, HEAD_RADIUS, fishAngle - 80);
        PointF bottomLeftPoint = calculatePoint(bodyBottomCenterPoint, BIG_CIRCLE_RADIUS, fishAngle + 90);
        PointF bottomRightPoint = calculatePoint(bodyBottomCenterPoint, BIG_CIRCLE_RADIUS, fishAngle - 90);
        // 二阶贝塞尔曲线的控制点，决定鱼的胖瘦
        PointF contralLeft = calculatePoint(headPoint, BODY_LENGHT * 0.56f, fishAngle + 130);
        PointF contralRight = calculatePoint(headPoint, BODY_LENGHT * 0.56f, fishAngle - 130);
        mPath.reset();
        mPath.moveTo(topLeftPoint.x, topLeftPoint.y);
        mPath.quadTo(contralLeft.x, contralLeft.y, bottomLeftPoint.x, bottomLeftPoint.y);
        mPath.lineTo(bottomRightPoint.x, bottomRightPoint.y);
        mPath.quadTo(contralRight.x, contralRight.y, topRightPoint.x, topRightPoint.y);
        mPaint.setColor(Color.argb(BODY_ALPHA, 244, 92, 71));
        canvas.drawPath(mPath, mPaint);
    }

    /**
     * 设置透明度的方法
     */
    @Override
    public void setAlpha(int alpha) {
        // 设置Drawable的透明度，一般情况下将此alpha值设置给Paint
        mPaint.setAlpha(alpha);
    }

    /**
     * 设置了一个颜色过滤器，那么在绘制出来之前，被绘制内容的每一个像素都会被颜色过滤器改变
     */
    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        // 设置颜色滤镜，一般情况下将此值设置给Paint
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    /**
     * 这个值，可以根据setAlpha中设置的值进行调整。比如，alpha == 0时设置为
     * PixelFormat.TRANSPARENT。
     * 在alpha == 255时设置为PixelFormat.OPAQUE。在其他时候设置为
     * PixelFormat.TRANSLUCENT。
     * PixelFormat.OPAQUE:便是完全不透明，遮盖在他下面的所有内容 * PixelFormat.TRANSPARENT:透明，完全不显示任何东西
     * PixelFormat.TRANSLUCENT:只有绘制的地方才覆盖底下的内容
     * /
     *
     * @Override public int getOpacity() {
     * return PixelFormat.TRANSLUCENT;
     * }
     * <p>
     * /**
     * 在View使用wrap_content的时候，设置固定宽度，默认为-1
     */
    @Override
    public int getIntrinsicWidth() {
        return (int) (8.38f * HEAD_RADIUS);
    }

    /**
     * 在View使用wrap_content的时候，设置固定高度，默认为-1
     */
    @Override
    public int getIntrinsicHeight() {
        return (int) (8.38f * HEAD_RADIUS);
    }

    public PointF getMiddlePoint() {
        return middlePoint;
    }

    public PointF getHeadPoint() {
        return headPoint;
    }

    public static float getHeadRadius() {
        return HEAD_RADIUS;
    }

    public void setFishStartAngle(float angle) {
        this.fishStartAngle = angle;
    }

    public float getFrequence() {
        return frequence;
    }

    public void setFrequence(float frequence) {
        this.frequence = frequence;
    }
}
