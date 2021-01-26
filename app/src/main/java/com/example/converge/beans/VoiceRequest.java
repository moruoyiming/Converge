package com.example.converge.beans;

public class VoiceRequest {
    String pid;
    String nonce;
    String appKey;
    long timestamp;
    String inputStr;
    int format;
    String sign;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getInputStr() {
        return inputStr;
    }

    public void setInputStr(String inputStr) {
        this.inputStr = inputStr;
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "Requsts{" +
                "pid='" + pid + '\'' +
                ", nonce='" + nonce + '\'' +
                ", appKey='" + appKey + '\'' +
                ", timestamp=" + timestamp +
                ", inputStr='" + inputStr + '\'' +
                ", format='" + format + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
