package com.example.converge.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {

    private List<List<View>> allLines = new ArrayList<>();//记录所有的行 ,
    private List<Integer> lineHeghts = new ArrayList<>();//多行
    private int lineSpacing = 20;
    private int itemSpacing = 20;

    //new FlowLayout
    public FlowLayout(Context context) {
        super(context);
    }

    // xml  序列化 key-value inflater解析 xml 反射使用
    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 自定义主题 style
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 第四个参数 为自定义属性
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //度量 两个参数  由父类调用measure时触发传入
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        clear();
        int paddingleft = getPaddingLeft();
        int paddingright = getPaddingRight();
        int paddingtop = getPaddingTop();
        int paddingbottom = getPaddingBottom();

        //获取父类的父类 的参考值  getSize  获取 30 位的值
        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);


        //计算 宽高 引入参数
        List<View> views = new ArrayList<>();//保存一行中的所有的view
        int lineWidthUsed = 0;//记录这行使用的宽size
        int lineHeight = 0;//记录行高

        int parentNeededWidth = 0;//measure过程中，子view的要求的父viewGroup的宽
        int parentNeededHeight = 0;//measure过程中，子view的要求的父viewGroup的高


        //1.先度量子类
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            LayoutParams layoutParams = child.getLayoutParams();
            if (child.getVisibility() != View.GONE) {
                //widthMeasureSpec 为父类传入
                //padding 获取父类 padding
                //layoutParams.width 需要的值
                int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, paddingleft + paddingright, layoutParams.width);
                int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, paddingtop + paddingbottom, layoutParams.height);
                //传入的只是参考值
                child.measure(childWidthMeasureSpec, childHeightMeasureSpec);


                //获取子view度量宽高
                int childMeasureWidth = child.getMeasuredWidth();
                int childMeasureHeight = child.getMeasuredHeight();


                //换行操作
                if ((childMeasureWidth + lineWidthUsed + lineSpacing) > selfWidth) {//超过一行 换行 清数据

                    //换行，记录
                    allLines.add(views);
                    lineHeghts.add(lineHeight);

                    parentNeededWidth = Math.max(parentNeededWidth, lineWidthUsed + lineSpacing);
                    parentNeededHeight = parentNeededHeight + lineHeight + itemSpacing;
                    views = new ArrayList<>();
                    lineWidthUsed = 0;
                    lineHeight = 0;

                }


                //view是分行layout的，所以要记录每一行有哪些view，这样可以方便layout布局
                views.add(child);
                lineWidthUsed = lineWidthUsed + childMeasureWidth + lineSpacing;
                lineHeight = Math.max(lineHeight, childMeasureHeight);


                //处理最后一行数据
                if (i == childCount - 1) {
                    allLines.add(views);
                    lineHeghts.add(lineHeight);
                    parentNeededHeight = parentNeededHeight + lineHeight + itemSpacing;
                    parentNeededWidth = Math.max(parentNeededWidth, lineWidthUsed + lineSpacing);
                }
            }
        }

        //2.度量自己 并保存   所有行 就是高，最宽的 为宽
        //需要考虑父类的 宽高
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int realWidth = (widthMode == MeasureSpec.EXACTLY) ? selfWidth : parentNeededWidth;
        int realHeight = (heightMode == MeasureSpec.EXACTLY) ? selfHeight : parentNeededHeight;
        //保存度量的具体值
        setMeasuredDimension(realWidth, realHeight);

//        难点  widthMeasureSpec heightMeasureSpec width height 值的计算？
    }

    //布局
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int curL = getPaddingLeft();
        int curT = getPaddingTop();
        int lineCount = allLines.size();//确定多少行


        for (int i = 0; i < lineCount; i++) {
            List<View> views = allLines.get(i);
            int lineHeight = lineHeghts.get(i);

            //布局一行
            for (int j = 0; j < views.size(); j++) {
                View view = views.get(j);
                int left = curL;
                int top = curT;

                int right = left + view.getMeasuredWidth();//view.getWidth 区别？
                int bottom = top + view.getMeasuredHeight();
//                layout() 布局之后的值 通过getWidth getHight获取
//                getMeasureWidth 为当measure()调用时，会生成一个有效的度量值。
                view.layout(left, top, right, bottom);

                //新的左边界 等上一个view 的右边界+间距
                curL = right + lineSpacing;
            }

            //布局一行完毕
            curT = curT + lineHeight + itemSpacing;
            curL = getPaddingLeft();

        }


    }

    private void clear() {
        allLines.clear();
        lineHeghts.clear();
    }
}
