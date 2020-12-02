package com.example.basics.swing;

import java.applet.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

/**
 * 编写一个小程序，小应用程序窗口有一个按钮，点击这个按钮时，点击按钮的次数会显示在按钮上
 */
public class ButtonNumberTest extends Applet {
    static JFrame mw;
    static JButton buttonx;
    Sqr s = new Sqr();

    public void init() {
        mw = new JFrame("按钮点击次数统计");
        mw.setSize(200, 150);
        mw.setLayout(new FlowLayout());
        buttonx = new JButton("0");
        mw.add(buttonx);
        buttonx.addActionListener(s);
        mw.setVisible(true);
    }
    class Sqr implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == ButtonNumberTest.buttonx) {
                int n = Integer.parseInt(ButtonNumberTest.buttonx.getLabel());
                n = n + 1;
                ButtonNumberTest.buttonx.setLabel(String.valueOf(n));
            } else {
            }
        }
    }
}

