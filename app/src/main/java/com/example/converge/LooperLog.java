package com.example.converge;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

public class LooperLog {
    private static LooperLog sInstance = new LooperLog();
    private HandlerThread mLogThread = new HandlerThread("log");
    private Handler mIoHandler;
    private static final long TIME_BLOCK = 1000L;

    private LooperLog() {
        mLogThread.start();
        mIoHandler = new Handler(mLogThread.getLooper());
    }

    private static Runnable mLogRunnable = new Runnable() {
        @Override
        public void run() {
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = Looper.getMainLooper().getThread().getStackTrace();
            for (StackTraceElement s : stackTrace) {
                sb.append(s.toString() + "\n");
            }
            Log.i("LogPrinter--", sb.toString());
        }
    };

    public static LooperLog getInstance() {
        return sInstance;
    }


    public void startPrintLog() {
        mIoHandler.postDelayed(mLogRunnable, TIME_BLOCK);
    }

    public void canclePrintLog() {
        mIoHandler.removeCallbacks(mLogRunnable);
    }
}