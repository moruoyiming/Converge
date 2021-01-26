package com.example.converge.note.javabasics.json.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public enum Num {
    ONE,TWO,  THREE;

    public void printValues() {
        System.out.println(ONE + " ONE.ordinal " + ONE.ordinal());
        System.out.println(TWO + " TWO.ordinal " + TWO.ordinal());
        System.out.println(THREE + " THREE.ordinal " + THREE.ordinal());
    }

    public static void testSerializable() throws Exception {
        File file = new File("num.out");
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
//        oos.writeObject(Num.ONE);
//        oos.close();
//        Num.ONE.printValues();
        System.out.println("=========反序列化后=======");

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Num s1 = (Num) ois.readObject();
        s1.printValues();
        ois.close();
//        ONE ONE.ordinal 1
//        TWO TWO.ordinal 0
//        THREE THREE.ordinal 2
//                =========反序列化后=======
//        ONE ONE.ordinal 0
//        TWO TWO.ordinal 1
//        THREE THREE.ordinal 2

    }

    public static void main(String... args) throws Exception {
        //TODO:
        testSerializable();
    }

}
