package com.example.basics.file;

import java.awt.*;

import javax.swing.*;

import java.io.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;

/**
 * 应用程序利用文件对话框确定数据文件。程序首先弹出一个界面，有两个按钮：打开文件和保存文件。当
 * 点击打开文件按钮时，程序弹出一个打开文件对话框，当点击保存文件时，弹出保存文件对话框。程序还继承 FileFilter
 * 类，定义了 MyFileFilter 类，程序利用这个类设置文件对话框的筛选条件
 */
public class Example9_6 {
    public static void main(String arg[]) {
        FrameFiledialog f = new FrameFiledialog();
    }
}

class FrameFiledialog extends JFrame implements ActionListener {
    JFileChooser filedialog = null;
    JLabel lable = new JLabel(" ", JLabel.CENTER);
    JButton b1, b2;
    JTextArea text;

    FrameFiledialog() {
        super("带文件对话框的窗口");
        Container con = this.getContentPane();
        con.setLayout(new BorderLayout());
        con.setSize(40, 50);
        JPanel p = new JPanel();
        b1 = new JButton("打开文件");
        b2 = new JButton("保存文件");
        b1.addActionListener(this);
        b2.addActionListener(this);
        p.add(b1);
        p.add(b2);
        text = new JTextArea(20, 30);
        JScrollPane jsp = new JScrollPane(text);
        filedialog = new JFileChooser("D:\\workspace");
        filedialog.setControlButtonsAreShown(true);
        filedialog.addChoosableFileFilter(new MyFileFilter("txt"));
        filedialog.addChoosableFileFilter(new MyFileFilter("java"));
        text.setBackground(Color.cyan);
        con.add(jsp, BorderLayout.CENTER);
        con.add(lable, BorderLayout.NORTH);
        con.add(p, BorderLayout.SOUTH);
        this.setVisible(true);
        this.pack();
    }

    public void actionPerformed(ActionEvent e) {
        File file = null;
        int result;
        if (e.getSource() == b1) {//打开文件
            filedialog.setDialogTitle("打开文件");
            result = filedialog.showOpenDialog(this);
            text.setText("");
            if (result == JFileChooser.APPROVE_OPTION) {
                file = filedialog.getSelectedFile();
                lable.setText("你选择打开的文件名称是：" + file.getName());
            } else if (result == JFileChooser.CANCEL_OPTION) {
                lable.setText("你没有选择任何文件");
            }
            FileInputStream fileStream = null;
            if (file != null) {
                try {
                    fileStream = new FileInputStream(file);
                } catch (FileNotFoundException nfe) {
                    lable.setText("文件没有找到");
                    return;
                }
                int readByte;
                try {
                    while ((readByte = fileStream.read()) != -1)
                        text.append(String.valueOf((char) readByte));
                    fileStream.close();
                } catch (IOException ie) {
                    lable.setText("读取文件出错");
                }
            }
        } else if (e.getSource() == b2) {//保存文件
            filedialog.setDialogTitle("保存文件");
            result = filedialog.showSaveDialog(this);
            file = null;
            String fileName;
            if (result == JFileChooser.APPROVE_OPTION) {
                file = filedialog.getSelectedFile();
                lable.setText("你选择保存的文件名称是：" + file.getName());
            } else if (result == JFileChooser.CANCEL_OPTION) {
                lable.setText("你没有选择任何文件");
            }
            FileOutputStream fileStream = null;
            if (file != null) {
                try {
                    fileStream = new FileOutputStream(file);
                } catch (FileNotFoundException nfe) {
                    lable.setText("文件没有发现");
                    return;
                }
                String content = text.getText();
                try {
                    fileStream.write(content.getBytes());
                    fileStream.close();
                } catch (IOException ie) {
                    lable.setText("写文件出错");
                }
            }
        }
    }
}

class MyFileFilter extends FileFilter {
    String ext;

    MyFileFilter(String t) {
        ext = t;
    }

    public boolean accept(File file) {
        if (file.isDirectory()) return true;
        String fn = file.getName();
        int index = fn.lastIndexOf('.');
        if (index > 0 && index < fn.length() - 1) {
            String extension = fn.substring(index + 1).toLowerCase();
            if (extension.equals(ext)) return true;
        }
        return false;
    }

    public String getDescription() {
        if (ext.equals("java")) {
            return "JAVA Source File(*.java)";
        }
        if (ext.equals("txt")) {
            return "Txt File(*.txt)";
        }
        return "";
    }
} 