package com.example.basics.swing;

import javax.swing.*;

import java.awt.*;

public class J505 {
    public static void main(String[] args) {
        JLabel label1, label2, label3, label4, label5;
        JFrames mw = new JFrames("我是一个窗口");//创建一个窗口容器对象
        mw.setSize(250, 200);
        Container con = mw.getContentPane();
        con.setLayout(new BorderLayout());
        label1 = new JLabel("东标签");//默认左对齐
        label2 = new JLabel("南标签", JLabel.CENTER);
        label3 = new JLabel("西标签");
        label4 = new JLabel("北标签", JLabel.CENTER);
        label5 = new JLabel("中标签", JLabel.CENTER);
        con.add(label1, "East");
        con.add(label2, "South");
        con.add(label3, "West");
        con.add(label4, "North");
        con.add(label5, "Center");
        mw.setVisible(true);
    }
} 