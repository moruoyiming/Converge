package com.example.inject;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@InjectEvent(listenerSetter = "setOnClickListener", listenerType = View.OnClickListener.class, methodName = "onClick")
public @interface OnClick {
    int[] value();
}
