package com.example.basics.swing;

import javax.swing.*;

import java.awt.*;

public class Example5_2 {
    public static MyWindowDemo mw1;
    public static MyWindowDemo mw2;

    public static void main(String args[]) {
        JButton butt1 = new JButton("我是一个按钮");
        String name1 = "我的第一个窗口";
        String name2 = "我的第二个窗口";
        mw1 = new MyWindowDemo(name1, butt1, Color.blue, 350, 450);
        mw1.setVisible(true);
        JButton butt2 = new JButton("我是另一个按钮");
        mw2 = new MyWindowDemo(name2, butt2, Color.magenta, 300, 400);
        mw2.setVisible(true);
    }
}

class MyWindowDemo extends JFrames {
    public MyWindowDemo(String name, JButton button, Color c, int w, int h) {
        super();
        setTitle(name);
        setSize(w, h);
        Container con = getContentPane();
        con.add(button);
        con.setBackground(c);
    }
} 