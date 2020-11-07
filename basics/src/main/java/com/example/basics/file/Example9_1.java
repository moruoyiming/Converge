package com.example.basics.file;

import java.io.*;

/**
 * 一个文件复制应用程序，将某个文件的内容全部复制另一个文件。
 */
class Example9_1 {
    public static void main(String arg[]) {
        File inputFile = new File("file1.txt");
        File outputFile = new File("file2.txt");
        int ch;
        try {
            FileReader in = new FileReader(inputFile);
            FileWriter out = new FileWriter(outputFile);
            System.out.println("文件复制程序开始工作");
            while ((ch = in.read()) != -1) {
                out.write(ch);
            }
            in.close();
            out.close();
            System.out.println("文件复制程序工作结束");
        } catch (FileNotFoundException e1) {
            System.out.println("文件没有找到" + e1);
        } catch (IOException e2) {
            System.out.println("File read Error:" + e2);
        }
    }
}