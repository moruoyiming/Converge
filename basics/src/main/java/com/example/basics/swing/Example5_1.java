package com.example.basics.swing;

import javax.swing.*;

public class Example5_1 {
    public static void main(String args[]) {
        JFrames mw = new JFrames("我的第一个窗口");
        mw.setSize(250, 200);
        JButton button = new JButton("我是一个按钮");
        mw.getContentPane().add(button);
        mw.setVisible(true);
    }
} 