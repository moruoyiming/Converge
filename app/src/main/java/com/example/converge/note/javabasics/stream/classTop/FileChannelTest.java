package com.example.converge.note.javabasics.stream.classTop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.Duration;
import java.time.Instant;

public class FileChannelTest {

	public static void main(String[] args) {
		File sourceFile = new File("D://alvin//IOtest//file1.mp4");
		File targetFile = new File("D://file1-1.mp4");
		targetFile.deleteOnExit();
		try {
			targetFile.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}

		copyFileByStream(sourceFile, targetFile);
		copyFileByFileChannel(sourceFile, targetFile);
	}

	private static void copyFileByFileChannel(File sourceFile,File targetFile){
		Instant begin = Instant.now();

		RandomAccessFile randomAccessSourceFile;
		RandomAccessFile randomAccessTargetFile;

		try {
			randomAccessSourceFile = new RandomAccessFile(sourceFile, "r");
			randomAccessTargetFile = new RandomAccessFile(targetFile, "rw");

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		FileChannel sourceFileChannel = randomAccessSourceFile.getChannel();
		FileChannel targetFileChannel = randomAccessTargetFile.getChannel();

		ByteBuffer byteBuffer = ByteBuffer.allocate(1024*1024);
		try {
			while(sourceFileChannel.read(byteBuffer) != -1) {
				byteBuffer.flip();
				targetFileChannel.write(byteBuffer);
				byteBuffer.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				sourceFileChannel.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			try {
				targetFileChannel.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("total spent: " + Duration.between(begin, Instant.now()).toMillis());
	}

	private static void copyFileByStream(File sourceFile,File targetFile) {
		Instant begin = Instant.now();

		FileInputStream fis;
		FileOutputStream fos;

		try {
			fis = new FileInputStream(sourceFile);
			fos = new FileOutputStream(targetFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		byte[] readed = new byte[1024*1024];
		try {
			while (fis.read(readed) != -1) {
				fos.write(readed);
			}
		} catch( IOException e){
			e.printStackTrace();
		} finally {
			try{
				fos.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			try {
				fis.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		System.out.println("total spent: " + Duration.between(begin, Instant.now()).toMillis());

	}

}
