package com.example.hotfix.note.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StarItemDecoration extends RecyclerView.ItemDecoration {
    private Paint textPaint;
    private Paint headPaint;
    private Rect textRect;
    private int groupHeaderHeight;

    public StarItemDecoration(Context context) {
        groupHeaderHeight = dp2px(context, 100);
        headPaint = new Paint();
        headPaint.setStyle(Paint.Style.FILL);
        headPaint.setColor(Color.BLUE);
        textPaint = new Paint();
        textPaint.setTextSize(50);
        textPaint.setColor(Color.RED);
        textRect = new Rect();
    }

    @Override
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(canvas, parent, state);
        if (parent.getAdapter() instanceof StarAdapter) {
            StarAdapter starAdapter = (StarAdapter) parent.getAdapter();
            int childCount = parent.getChildCount();
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            for (int i = 0; i < childCount; i++) {
                final View view = parent.getChildAt(i);
                int position = parent.getChildAdapterPosition(view);
                boolean isGroupHead = starAdapter.isGroupHead(position);
                if (isGroupHead && view.getTop() - groupHeaderHeight - parent.getPaddingTop() >= 0) {//如何判断itemview 是头部
                    Log.i("Tokyo", "onDraw " + starAdapter.isGroupHead(position) + " position  " + position + "  child.getTop() " + view.getTop());
                    canvas.drawRect(left, view.getTop() - groupHeaderHeight, right, view.getTop(), headPaint);
                    String groupName = starAdapter.getGroupName(position);
                    textPaint.getTextBounds(groupName, 0, groupName.length(), textRect);
                    canvas.drawText(groupName, left + 20, view.getTop() - groupHeaderHeight / 2 + textRect.height() / 2, textPaint);
                } else if (view.getTop() - groupHeaderHeight - parent.getPaddingTop() >= 0) {
                    canvas.drawRect(left, view.getTop() - 1, right, view.getTop(), headPaint);
                }
            }
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(canvas, parent, state);
        if (parent.getAdapter() instanceof StarAdapter) {
            StarAdapter starAdapter = (StarAdapter) parent.getAdapter();
            int position = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
            View itemView = parent.findViewHolderForAdapterPosition(position).itemView;
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            int top = parent.getPaddingTop();
            boolean isGroupHead = starAdapter.isGroupHead(position + 1);
            if (isGroupHead) {
                int bottom = Math.min(groupHeaderHeight, itemView.getBottom() - parent.getPaddingTop());
                canvas.drawRect(left, top, right, top + bottom, headPaint);
                String groupName = starAdapter.getGroupName(position);
                textPaint.getTextBounds(groupName, 0, groupName.length(), textRect);
                canvas.drawText(groupName, left + 20, top + bottom - groupHeaderHeight / 2 + textRect.height() / 2, textPaint);
            } else {
                canvas.drawRect(left, top, right, top + groupHeaderHeight, headPaint);
                String groupName = starAdapter.getGroupName(position);
                textPaint.getTextBounds(groupName, 0, groupName.length(), textRect);
                canvas.drawText(groupName, left + 20, top + groupHeaderHeight - groupHeaderHeight / 2 + textRect.height() / 2, textPaint);
            }
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getAdapter() instanceof StarAdapter) {
            StarAdapter starAdapter = (StarAdapter) parent.getAdapter();
            int position = parent.getChildAdapterPosition(view);
            if (starAdapter.isGroupHead(position)) {//如何判断itemview 是头部
                //如果是头部，预留更大的地方
                outRect.set(0, groupHeaderHeight, 0, 0);
            } else {
                outRect.set(0, 1, 0, 0);
            }

        }
    }

    private int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale * 0.5f);
    }
}
