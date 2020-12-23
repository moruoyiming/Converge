package com.example.patch;

/**
 * @author Lance* @date 2019-09-03
 */
public class PatchExtension {

    boolean debugOn;
    String output;
    String applicationName;


    public PatchExtension() {
        debugOn = false;
    }

    public void setDebugOn(boolean debugOn) {
        this.debugOn = debugOn;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}
