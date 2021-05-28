package com.example.converge;

import android.util.Log;
import android.view.Choreographer;

public class FPSFrameCallback implements Choreographer.FrameCallback {
    private static final String TAG = "FPS_TEST";
    private long mLastFrameTimeNanos = 0;
    private long mFrameIntervalNanos;

    public FPSFrameCallback(long lastFrameTimeNanos) {
        mLastFrameTimeNanos = lastFrameTimeNanos;
        mFrameIntervalNanos = (long) (1000000000 / 60.0);//16ms
    }

    @Override
    public void doFrame(long frameTimeNanos) {
        //初始化时间
        if (mLastFrameTimeNanos == 0) {
            mLastFrameTimeNanos = frameTimeNanos;
        }
        final long jitterNanos = frameTimeNanos - mLastFrameTimeNanos;
        if (jitterNanos >= mFrameIntervalNanos) {
            final long skippedFrames = jitterNanos / mFrameIntervalNanos;
            if (skippedFrames > 30) {
                Log.i(TAG, "Skipped " + skippedFrames + " frames!  "
                        + "The application may be doing too much work on its main thread.");
            }
        }
        mLastFrameTimeNanos = frameTimeNanos;
        //注册下一帧回调
        Choreographer.getInstance().postFrameCallback(this);
    }
}
