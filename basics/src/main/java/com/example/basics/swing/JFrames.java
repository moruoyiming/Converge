package com.example.basics.swing;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JButton;

public class JFrames extends Applet {
    public static void main(String[] args) {
        javax.swing.JFrame jFrame = new javax.swing.JFrame("JFrameDemo");
        JButton jButton=new JButton("Press me");
        jFrame.getContentPane().add(jButton, BorderLayout.CENTER);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void destroy() {
        super.destroy();
    }


    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
    }
}
