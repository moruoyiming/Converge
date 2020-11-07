package com.example.basics.swing;

import java.applet.*;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

/**
 * 创建一个有文本框和三个按钮的程序。当按下某个按钮时，使不同的文字显示在文本框中
 */
public class ButtonTest extends Applet implements ActionListener {
    JTextField text;
    JButton b1, b2, b3;

    public void init() {
        setSize(250, 150);
        text = new JTextField("", 20);
        b1 = new JButton("中文");
        b2 = new JButton("英文");
        b3 = new JButton("数字");
        text.setEditable(false);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        add(text);
        add(b1);
        add(b2);
        add(b3);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1)
            text.setText("中文按钮");
        else if (e.getSource() == b2)
            text.setText("English");
        else if (e.getSource() == b3)
            text.setText("123456");
    }
} 