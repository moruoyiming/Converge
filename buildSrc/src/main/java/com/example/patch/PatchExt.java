package com.example.patch;

public class PatchExt {
    private boolean debugOn;
    private String applicationName;
    private String output;

    public PatchExt() {
    }

    public PatchExt(boolean debugOn, String applicationName, String output) {
        this.debugOn = debugOn;
        this.applicationName = applicationName;
        this.output = output;
    }
}
