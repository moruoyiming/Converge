package com.example.hotfix.note.class13.classTop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;

public class WriterAndStream {

	private static void testWriterAndStream(){
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(
//					new FileWriter("src/testtxt/writerAndStream.txt"));
					new OutputStreamWriter(
							new FileOutputStream(
									new File("src/testtxt/writerAndStream.txt")),"GBK"));
			
			bufferedWriter.write("�� �����й����װ���ĸ��");
			bufferedWriter.flush();
			bufferedWriter.close();
			System.out.println("end");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testWriterAndStream();
	}

}
