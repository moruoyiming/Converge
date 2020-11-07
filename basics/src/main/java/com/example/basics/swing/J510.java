package com.example.basics.swing;

import java.applet.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * 小应用程序计算从起始整数到终止整数中是因子倍数的所有数。小程序容器用GridLayout布局将界面划分为 3行列，
 * 第一行是标签，第二行和第三行是两个 Panel。设计两个 Panel 容器类 Panel1,Panel2,并分别用 GridLayout 布局划分。Panel1
 * 为 1 行 6 列，Panel2 为 1 行 4 列。然后将标签和容器类 Panel1,Panel2 产生的组件加入到窗口的相应位置中。
 */

class Panel1 extends JPanel {
    JTextField text1, text2, text3;

    Panel1() {//构造方法。当创建 Panel 对象时，Panel 被初始化为有三个标签
        //三个文本框，布局为 GridLayout(1,6)
        text1 = new JTextField(10);
        text2 = new JTextField(10);
        text3 = new JTextField(10);
        setLayout(new GridLayout(1, 6));
        add(new JLabel("起始数", JLabel.RIGHT));
        add(text1);
        add(new JLabel("终止数", JLabel.RIGHT));
        add(text2);
        add(new JLabel("因子", JLabel.RIGHT));
        add(text3);
    }
}

class Panel2 extends JPanel {//扩展 Panel 类
    JTextArea text;
    JButton Button;

    Panel2() {//构造方法。当创建 Panel 对象时，Panel 被初始化为有一个标签
        //一个文本框，布局为 GridLayout(1,4)
        text = new JTextArea(4, 10);
        text.setLineWrap(true);
        JScrollPane jsp = new JScrollPane(text);
        Button = new JButton("开始计算");
        setLayout(new GridLayout(1, 4));
        add(new JLabel("计算结果：", JLabel.RIGHT));
        add(jsp);
        add(new Label());
        add(Button);
    }
}

public class J510 extends Applet implements ActionListener {
    Panel1 panel1;
    Panel2 panel2;

    public void init() {
        setLayout(new GridLayout(3, 1));
        setSize(400, 200);
        panel1 = new Panel1();
        panel2 = new Panel2();
        add(new JLabel("计算从起始数到终止数是因子倍数的数", JLabel.CENTER));
        add(panel1);
        add(panel2);
        (panel2.Button).addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == (panel2.Button)) {
            long n1, n2, f, count = 0;
            n1 = Long.parseLong(panel1.text1.getText());
            n2 = Long.parseLong(panel1.text2.getText());
            f = Long.parseLong(panel1.text3.getText());
            for (long i = n1; i <= n2; i++) {
                if (i % f == 0)
                    panel2.text.append(String.valueOf(i) + "");
            }
        }
    }
} 