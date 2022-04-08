package com.example.converge.note.androidbasics.inject;

/**
 * @Date: 2022/4/8
 * @Time: 16:41
 * @Author: Jian
 */
public class Data {

    String result;

    public Data(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Data{" +
                "result='" + result + '\'' +
                '}';
    }
}
