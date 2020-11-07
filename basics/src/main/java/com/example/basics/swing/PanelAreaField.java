package com.example.basics.swing;

import java.util.*;
import java.applet.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;



public class PanelAreaField extends Applet implements ActionListener {
    Panel1 panel1;
    Panel2 panel2;
    JTextArea textA;

    public void init() {
        setLayout(new GridLayout(3, 1));
        setSize(400, 200);
        panel1 = new Panel1();
        panel2 = new Panel2();
        textA = new JTextArea(4, 10);
        textA.setLineWrap(true);
        JScrollPane jsp = new JScrollPane(textA);
        add(jsp);
        add(panel1);
        add(panel2);
        (panel2.button1).addActionListener(this);
        (panel2.button2).addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == (panel2.button1)) {
            (panel1.label).setText("输入数的和");
            String s = textA.getText();
            StringTokenizer words = new StringTokenizer(s);
            int n = words.countTokens(), sum = 0, i;
            for (i = 0; i <= n - 1; i++) {
                String temp = words.nextToken();
                sum += Integer.parseInt(temp);
            }
            (panel1.textF).setText(" " + sum);
        } else if (e.getSource() == (panel2.button2)) {
            (panel1.label).setText("输入数的平均值");
            String s = textA.getText();
            StringTokenizer words = new StringTokenizer(s);
            int n = words.countTokens(), i, sum = 0;
            for (i = 0; i <= n - 1; i++) {
                String temp = words.nextToken();
                sum += Integer.parseInt(temp);
            }
            (panel1.textF).setText(" " + sum / n);
        }
    }

    class Panel1 extends JPanel {
        JLabel label;
        JTextField textF;

        Panel1() {
            label = new JLabel("", JLabel.RIGHT);
            textF = new JTextField(6);
            setLayout(new GridLayout(1, 2));
            add(label);
            add(textF);
        }
    }

    class Panel2 extends JPanel {
        JButton button1, button2;

        Panel2() {
            button1 = new JButton("求 和");
            button2 = new JButton("求平均值");
            setLayout(new GridLayout(1, 2));
            add(button1);
            add(button2);
        }
    }
} 