package com.cocos.basewebview.widget;

public interface BaseProgressSpec {
    void show();

    void hide();

    void reset();

    void setProgress(int newProgress);
}
