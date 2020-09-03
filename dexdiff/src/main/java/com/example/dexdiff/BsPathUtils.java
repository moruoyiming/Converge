package com.example.dexdiff;

public class BsPathUtils {

    static {
        System.loadLibrary("bspatch");
    }

    public static native int patch(String oldApk, String newApk, String patch);
}
