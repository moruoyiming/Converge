package com.example.hotfix.note.class19;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StarItemDecoration extends RecyclerView.ItemDecoration {
    private Paint paint;
    private Paint textPaint;
    private Rect rect;

    public StarItemDecoration() {

        paint = new Paint();
        textPaint = new Paint();
        textPaint.setColor(Color.RED);
        rect = new Rect();
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (parent.getAdapter() instanceof StarAdapter) {
            StarAdapter starAdapter = (StarAdapter) parent.getAdapter();

        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (parent.getAdapter() instanceof StarAdapter) {
            StarAdapter starAdapter = (StarAdapter) parent.getAdapter();

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
                outRect.set(0, 180, 0, 0);
            } else {
                outRect.set(0, 1, 0, 0);
            }

        }
    }
}
