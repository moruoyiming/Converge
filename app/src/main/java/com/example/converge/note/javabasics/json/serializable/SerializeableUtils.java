package com.example.converge.note.javabasics.json.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeableUtils {

    public static  <T> byte[] serialize(T t) throws Exception{
        //TODO:
        //  a.静态成员变量属于类不属于对象，所以不会参与序列化(对象序列化保存的是对象的“状态”，也就是它的成员变量，因此序列化不会关注静态变量)
        //  b.用transient关键字标记的成员变量不参与序列化(在被反序列化后，transient 变量的值被设为初始值，如 int 型的是 0，对象型的是 null)

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(t);
        return out.toByteArray();
    }

    public static <T> T deserialize(byte[] bytes)throws Exception{
        //TODO:
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        T t = (T)ois.readObject();
        return t;
    }

    /**
     * 序列化对象
     *
     * @param obj
     * @param path
     * @return
     */
    synchronized public static boolean saveObject(Object obj, String path) {
        if (obj == null) {
            return false;
        }
        ObjectOutputStream oos = null;
        try {
            // 创建序列化流对象
            oos = new ObjectOutputStream(new FileOutputStream(path));
           //序列化
            oos.writeObject(obj);
            oos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    // 释放资源
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 反序列化对象
     *
     * @param path
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked ")
    synchronized public static <T> T readObject(String path) {
        ObjectInputStream ojs = null;
        try {
            // 创建反序列化对象
            ojs = new ObjectInputStream(new FileInputStream(path));
            // 还原对象
            return (T) ojs.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ojs!=null){
                try {
                    // 释放资源
                    ojs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
