package com.example.basics.file;

import java.io.*;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

/**
 * 说明 BufferedReader 类的用法的应用程序。
 */
class MyWindow extends JFrame implements ActionListener {
    JTextArea text;
    BufferedReader in;
    JButton button1;
    FileReader file;

    MyWindow() {
        super("缓冲区中读取");
        Container con = this.getContentPane();//获得内容面板
        con.setSize(100, 400);
        con.setLayout(new BorderLayout());
        button1 = new JButton("读取文件");
        button1.addActionListener(this);
        text = new JTextArea(20, 20);
        text.setBackground(Color.cyan);
        JScrollPane jsp = new JScrollPane(text);
        con.add(jsp, BorderLayout.CENTER);
        con.add(button1, "South");
        this.setVisible(true);
        this.pack();
        try {
            File f = new File("Example9_3.java");
            file = new FileReader(f);// 或者 file = new FileReader(“Example9_3.java”);
            in = new BufferedReader(file);
        } catch (FileNotFoundException e1) {
            text.setText("文件没有找到");
            button1.removeActionListener(this);
        }
    }

    public void actionPerformed(ActionEvent e) {
        String s;
        if (e.getSource() == button1)
            try {
                while ((s = in.readLine()) != null)
                    text.append(s + '\n');
            } catch (IOException exp) {
            }
    }
}

public class Example9_2 {
    public static void main(String arg[]) {
        MyWindow myWin = new MyWindow();
    }

} 