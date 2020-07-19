package com.example.hotfix.note.class13.classTop;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ObjectStream {
	private static File newFile(String path){
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return file;
	}
	
	private static void writeObject(){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream("src/testtxt/object.txt"));
			for(int i = 0; i < 10; i++){
				oos.writeObject(new Person("ŷ����[" + i +"]", i));
			}
			oos.writeObject(null);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void readObject() {
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream(newFile("src/testtxt/object.txt"))));
			while (ois.available() != -1) {
				try {
					Object object = ois.readObject();
					Person person = (Person) object;
					System.out.println(person.toString());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			ois.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void writeObjectByArray(){
		try {
			ObjectOutputStream oos = new ObjectOutputStream( 
					new FileOutputStream(newFile("src/testtxt/objectArrays.txt")));
			Person[] persons = new Person[10];
			for(int i = 0; i < 10; i++){
				Person person = new Person("���߹�[" + i + "]", i);
				persons[i] = person;
			}
			oos.writeObject(persons);
			oos.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * ͨ������д�����
	 */
	private static void writeObjectByList() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream("src/testtxt/objectByList.txt"));
			List<Person> persons=new ArrayList<Person>();
			for (int i = 1; i < 10; i++) {
				Person person = new Person("ŷ���� List[" + (20+i)+"]", 20+i);
				persons.add(person);
			}
			//д��List
			oos.writeObject(persons);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	


	public static void main(String[] args) {
//		writeObject();
//		writeObjectByArray();
//		writeObjectByList();
		
		readObject();
	}

}
