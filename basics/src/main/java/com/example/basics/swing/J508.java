package com.example.basics.swing;

import java.applet.*;

import javax.swing.*;

import java.awt.event.*;

/**
 * 小应用程序有两个文本框。一个文本用于输入一个整数，另一个文本框显示这个整数的平方值。程序用字符串转基本
 * 类型的方法 Long.parseLong(text1.getText())，读取文本框 text1 中的字符串，并将它转换成整数。程序用 Sqr 类的实例作
 * 为监视器，但为了让监视器能访问主类的变量，主类中的变量被声明为类变量，并且不设置访问权限。
 */
public class J508 extends Applet {
    static JTextField text1, text2;
    Sqr s = new Sqr();//创建监视器

    public void init() {
        text1 = new JTextField(10);
        text2 = new JTextField(10);
        add(text1);
        add(text2);
        text1.addActionListener(s);//类 Sqr 的实例 s 作为 text1 的监视器
    }
}

class Sqr implements ActionListener {
    public void actionPerformed(ActionEvent e) {//实现接口 ActionListener
        if (e.getSource() == J508.text1) {
            long n = Long.parseLong(J508.text1.getText());
            //将 text1 的文本转换成 long 型数据
            J508.text2.setText(String.valueOf(n * n));
            //将 n*n 转化为字符串
        } else {
        }
    }
} 