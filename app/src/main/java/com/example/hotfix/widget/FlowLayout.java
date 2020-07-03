//package com.example.hotfix.widget;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.View;
//import android.view.ViewGroup;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class FlowLayout extends ViewGroup {
//
//    private List<List<View>> allLines = new ArrayList<>();//记录所有的行 ,
//    private List<Integer> lineHeghts = new ArrayList<>();//多行
//
//    //new FlowLayout
//    public FlowLayout(Context context) {
//        super(context);
//    }
//
//    // xml  序列化 key-value inflater解析 xml 反射使用
//    public FlowLayout(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    // 自定义主题 style
//    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    // 第四个参数 为自定义属性
//    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
//
//    //度量 两个参数  由父类调用measure时触发传入
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        clear();
//        int paddingleft = getPaddingLeft();
//        int paddingright = getPaddingRight();
//        int paddingtop = getPaddingTop();
//        int paddingbottom = getPaddingBottom();
//
//        //获取父类的父类 的参考值  getSize  获取 30 位的值
//        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);
//        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);
//
//        List<View> views = new ArrayList<>();
//        int widthused = 0;
//        int height = 0;
//        //先度量子类
//        int childCount = getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View childview = getChildAt(i);
//            LayoutParams layoutParams = childview.getLayoutParams();
//            //widthMeasureSpec 为父类传入
//            //padding 获取父类 padding
//            //layoutParams.width 需要的值
//            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, paddingleft + paddingright, layoutParams.width);
//            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, paddingtop + paddingbottom, layoutParams.height);
//            //传入的只是参考值
//            childview.measure(childWidthMeasureSpec, childHeightMeasureSpec);
//            views.add(childview);
//            //获取子view度量宽高
//            int childMeasureWidth = childview.getMeasuredWidth();
//            int childMeasureHeight = childview.getMeasuredHeight();
////            widthused=childMeasureHeight+paddingleft+paddingright;
//            if ((childMeasureWidth + paddingleft + paddingright) > selfWidth) {//超过一行 换行 清数据
//                widthused = 0;
//                height = 0;
//                allLines.add(views);
//            }
//        }
//
//        //度量自己 并保存   所有行 就是高，最宽的 为宽
//        //需要考虑父类的 宽高
//        setMeasuredDimension(width, height);
//
////        难点  widthMeasureSpec heightMeasureSpec width height 值的计算？
//    }
//
//    //布局
//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        int leftPadding = getPaddingLeft();
//        int topPadding = getPaddingTop();
//        int lines = allLines.size();//确定多少行
//        int curL = 0;
//        int curT = 0;
//        for (int i = 0; i < lines; i++) {
//            List<View> views = allLines.get(i);
//            int lineHeight = lineHeghts.get(i);
//            for (int j = 0; j < views.size(); j++) {
//                View view = views.get(j);
//                int left = curL;
//                int top = curT;
//                view.layout();
//            }
//
//        }
//
//
//    }
//
//    private void clear() {
//        allLines.clear();
//        lineHeghts.clear();
//    }
//}
