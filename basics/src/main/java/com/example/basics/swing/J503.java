package com.example.basics.swing;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class J503 {
    public static void main(String[] args) {
        ButtonDemo myButtonGUI = new ButtonDemo();//声明并创建按钮对象
        myButtonGUI.setVisible(true);
    }
}

class ButtonDemo extends JFrame implements ActionListener {
    public static final int Width = 250;
    public static final int Height = 200;

    ButtonDemo() {
        setSize(Width, Height);
        setTitle("按钮事件样例");
        Container conPane = getContentPane();
        conPane.setBackground(Color.BLUE);
        conPane.setLayout(new FlowLayout());//采用 FlowLayout 布局
        JButton redBut = new JButton("Red");
        redBut.addActionListener(this);//给 Red 按钮注册监视器
        conPane.add(redBut);//在窗口添加 Red 按钮
        JButton greenBut = new JButton("Green");
        greenBut.addActionListener(this);//给 Green 按钮注册监视器
        conPane.add(greenBut);//在窗口添加 Green 按钮
    }

    public void actionPerformed(ActionEvent e) {//实现接口处理事件的方法
        Container conPane = getContentPane();
        if (e.getActionCommand().equals("Red"))//是 Red 按钮事件
            conPane.setBackground(Color.RED);
        else if (e.getActionCommand().equals("Green"))//是 Green 按钮事件
            conPane.setBackground(Color.GREEN);
        else {
        }
    }
}