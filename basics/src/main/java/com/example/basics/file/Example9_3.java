package com.example.basics.file;

import java.io.*;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

class MyWindow2 extends JFrame implements ActionListener {
    JTextArea text;
    JButton button1;
    FileWriter writefile;
    BufferedWriter out;

    MyWindow2() {
        super("缓冲式样流的输出");
        Container con = this.getContentPane();//获得内容面板
        //con.setSize(100,400);
        con.setLayout(new BorderLayout());
        button1 = new JButton("写文件");
        button1.addActionListener(this);
        text = new JTextArea(20, 30);
        text.setBackground(Color.cyan);
        con.setSize(40, 40);
        con.add(text, "Center");
        con.add(button1, "South");
        con.setVisible(true);
// this pack();
        try {
            writefile = new FileWriter("line.txt");
            out = new BufferedWriter(writefile);
        } catch (IOException e) {
        }
    }

    public void actionPerformed(ActionEvent e) {
        String s;
        if (e.getSource() == button1)
            try {
                out.write(text.getText(), 0, (text.getText()).length());
                out.flush();
                text.setText(null);
                System.exit(0);
            } catch (IOException exp) {
                text.setText("文件写出错!\n");
                System.exit(-1);
            }
    }
}

public class Example9_3 {
    public static void main(String args[]) {
        MyWindow2 myWin = new MyWindow2();
        myWin.pack();
    }


} 