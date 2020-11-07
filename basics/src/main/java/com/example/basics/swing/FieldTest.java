package com.example.basics.swing;

import java.applet.*;

import javax.swing.*;

import java.awt.event.*;

/**
 * 编写一个有两个文本框的小应用程序，在第一个文本框输入英语单词，在第二个文本框会自动显示汉语解释；在第一个
 * 文本框输入汉语单词，在第二个文本框中显示英语解释。设英语单词表只有少许几个
 */
public class FieldTest extends Applet implements ActionListener {
    JTextField text1, text2;

    public void init() {
        setSize(250, 150);
        text1 = new JTextField(10);
        text2 = new JTextField(10);
        text1.addActionListener(this);
        text2.addActionListener(this);
        text2.setEditable(false);
        text1.requestFocus();
        add(text1);
        add(text2);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == text1) {
            String s = text1.getText();
            if (s.equals("中国")) {
                text2.setText("China");
            } else if (s.equals("China")) {
                text2.setText("中国");
            } else if (s.equals("美国")) {
                text2.setText("America");
            } else if (s.equals("America")) {
                text2.setText("美国");
            } else {
                text2.setText("查无此单词");
            }
        }
    }
} 