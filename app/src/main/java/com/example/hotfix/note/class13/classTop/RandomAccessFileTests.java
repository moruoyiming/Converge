package com.example.hotfix.note.class13.classTop;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTests {

private static final File file = new File("src\\testtxt\\raf.txt");
	
	/**
	 * ���ļ���д������
	 */
	public static void testRandomAccessFileWriter() throws IOException{
		//Ҫ�Ƚ������ļ�ɾ����������š�
		if(file.exists()){
			file.delete();
		}
		
		RandomAccessFile rsfWriter = new RandomAccessFile(file, "rw");
		
		//����ı��ļ���С���������Ὣ��һ���ַ���д��λ�ñ�ʶΪ10000��
		//Ҳ����˵�˺�ֻҪд�����ݡ����Ǵ�10001��ʼ�桢
		rsfWriter.seek(10000);
		printFileLength(rsfWriter);		//result: 0
		
		//��ı��ļ���С��ֻ�ǰ��ļ���size�ı䡢
		//��û�иı���һ��Ҫд������ݵ�λ�á�
		//����ע�͵���Ϊ����֤�����seek������˵������
		rsfWriter.setLength(10000);
		System.out.println("oo");
		printFileLength(rsfWriter);		//result: 0
		System.out.println("xx");
		//ÿ������ռ3���ֽڡ�д���ַ�����ʱ�����һ����¼д���ַ������ȵ������ֽ�
		rsfWriter.writeUTF("��ѧ����");	
		printFileLength(rsfWriter);		//result: 10014 
		
		//ÿ���ַ�ռ�����ֽ�
		rsfWriter.writeChar('a');
		rsfWriter.writeChars("abcde");
		printFileLength(rsfWriter);		//result: 10026
		
		//�ٴӡ��ļ�ָ�롱Ϊ5000�ĵط���һ������Ϊ100������ȫ��'a'���ַ�����
		//����file����Ȼ��10026����Ϊ���Ǵӡ��ļ�ָ�롱Ϊ5000�ĵط����Ǻ���
		//��200���ֽڡ��±겢û�г����ļ�����
		rsfWriter.seek(5000);
		char[] cbuf = new char[100];
		for(int i=0; i<cbuf.length; i++){
			cbuf[i] = 'a';
			rsfWriter.writeChar(cbuf[i]);
		}
		
		
		printFileLength(rsfWriter);	//result:  10026
		
		//�ٴӡ��ļ�ָ�롱Ϊ1000�ĵط�����һ������Ϊ100������ȫ��a���ֽ�����
		//����file����Ȼ��10026����Ϊ���Ǵӡ��ļ�ָ�롱Ϊ5000�ĵط����Ǻ���
		//��200���ֽڡ��±겢û�г����ļ�����
		byte[] bbuf = new byte[100];
		for (int i = 0; i < bbuf.length; i++) {
			bbuf[i] = 1;
		}
		rsfWriter.seek(1000);
		rsfWriter.writeBytes(new String(bbuf));
		printFileLength(rsfWriter);
	}
	
	/**
	 * ���ļ��ж�ȡ����
	 * ��������Ҫ��������ļ�����ʲô���ݡ����һ�Ҫ�����Щ������ʼ�ֽ��±ꡢ����
	 * 
	 * @throws IOException
	 */
	public static void testRandomAccessFileRead() throws IOException{
		/*
		 * ���ļ������ݼ�˵����
		 * 1����0��1000	Ϊ��
		 * 2����1001��1100��100��1
		 * 3����1101��5000�ǿ�
		 * 4����5001��5200���ַ�'a'
		 * 5����5201��10000�ǿ�
		 * 6����10001��10011���ַ���"�»�Ӧ"
		 * 7����10012��10023��"aabcde"
		 */
		RandomAccessFile rsfReader = new RandomAccessFile(file, "r");
		//�ɰ����Լ����ȡ�Ķ������ڵ�λ�á���������ȡ
		
		//��ȡ"��ѧ����"
		rsfReader.seek(10000);
		System.out.println(rsfReader.readUTF());
		
		//��ȡ100���ַ�'a'
		rsfReader.seek(5000);
		byte[] bbuf = new byte[200];
		rsfReader.read(bbuf);
		System.out.println(new String(bbuf));
		
		//��ȡ100��1
		byte[] bbuf2 = new byte[100];
		rsfReader.seek(1000);
		rsfReader.read(bbuf2, 0, 100);
		for(byte b : bbuf2){
			System.out.print(b);
		}
		
		//��ȡ�ַ�'aabcde'
		byte[] bbuf3 = new byte[12];
		rsfReader.seek(10014);
		rsfReader.read(bbuf3);
		System.out.println(new String(bbuf3));
	}
	/**
	 * ��ӡ�ļ�����
	 * @param rsfWriter ָ���ļ�������ļ���
	 * @throws IOException
	 */
	private static void printFileLength(RandomAccessFile rsfWriter)
			throws IOException {
		System.out.println("file length: " + rsfWriter.length() + "  file pointer: " + rsfWriter.getFilePointer());
	}
	
	public static void main(String[] args) throws IOException {
		testRandomAccessFileWriter();
		testRandomAccessFileRead();
	}


}
