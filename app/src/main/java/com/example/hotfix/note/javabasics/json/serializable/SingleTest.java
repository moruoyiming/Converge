package com.example.hotfix.note.javabasics.json.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SingleTest {

    static final String CurPath = System.getProperty("user.dir");

    public static void main(String ... args) throws Exception {
            //TODO:
//        Single instance = Single.getInstance();
//        System.out.println(instance.hashCode());
//        System.out.println(copyInstance(instance).hashCode());
//
//        System.out.println("=================反射======================");
//        //使用反射方式直接调用私有构造器
//        Class<Single> clazz = (Class<Single>)Class.forName("com.zero.serializabledemo.serializable.Single");
//        Constructor<Single> con = clazz.getDeclaredConstructor(null);
//        con.setAccessible(true);//绕过权限管理，即在true的情况下，可以通过构造函数新建对象
//        Single instance1 = con.newInstance();
//        Single instance2 = con.newInstance();
//        System.out.println(instance1.hashCode());
//        System.out.println(instance2.hashCode());

    }

    private static Single copyInstance(Single instance) throws Exception{
        //序列化会导致单例失效
        FileOutputStream fos = new FileOutputStream(CurPath+"/a.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(instance);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CurPath+"/a.txt"));
        Single single2 = (Single)ois.readObject();
        oos.close();
        ois.close();
        return single2;
    }
}

class Single implements Serializable {
    private static final long serialVersionUID = 1L;
    private static boolean flag = false;
    private Single(){
        synchronized (Single.class) {
            if (!flag) {
                flag = true;
            } else {
                throw new RuntimeException("单例模式被侵犯！");
            }

        }
    }

    private static Single single;

    public static Single getInstance(){
        if ( single == null ) {
            synchronized (Single.class) {
                if ( single == null ) {
                    single = new Single();
                }
            }

        }
        return single;
    }

    private Object readResolve() {
        return single;
    }
}
