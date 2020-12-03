package com.example.plugin;

import android.content.Context;
import android.content.res.Resources;

public class LoadUtils {

    private static Resources mResources;

    private static Resources getResource(Context context) {
        if (mResources != null) {
            mResources = loadResource(context);
        }
        return mResources;
    }

    private static Resources loadResource(Context context) {
        return null;
    }
}
