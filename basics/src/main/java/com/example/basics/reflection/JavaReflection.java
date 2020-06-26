package com.example.basics.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class JavaReflection {


    public static void main(String[] args) {
        //在反射调用中，如果想要对字节码文件进行解剖，必须要有字节码文件对象


        //1.Object类中的getClass方法
        //Person person=new Person();
        //Class class=person.getClass();

        //2.直接获取某个对象的Class 任何类型数据都具有一个静态的属性.class来获取其对应的class对象。
        //Class class=p.getClass();

        //3.使用Class 类中的forName静态方法，通过给定的类的字符串名称就可以获取该类。
        //Class class=Class.forName("what.com.zhikang.reflection.JavaReflection");


        //原始：new的时候，先根据被new的类的名称找寻该类的字节码文件，并加载进内存，
        //并创建该字节码文件对象，并接着创建该字节文件对应的Person对象。
        Person p = new Person();


        //反射：找寻该类的名称，并加载进内存，并产生Class对象
        //在产生类的对象
        String className = "what.com.zhikang.reflection.Person";
        try {
            //创建无参数实例
            Class clazz = Class.forName(className);
            Object object = clazz.newInstance();
            System.out.println(object.toString());
            //创建带参数的实例。 这时要通过Class对象获取指定的Constructor对象，再调用Constructor对象的newInstance()方法来创建实例
            Constructor constructor = clazz.getConstructor(String.class, Integer.class);
            Object obj = constructor.newInstance("xiaohei", 23);
            System.out.println(obj.toString());
            //反射方式获取字段(成员变量)
            //访问public字段
            Field field = clazz.getField("name");
            field.set(object, "小黑");
            Object ob = field.get(object);
            System.out.println(ob.toString());
            //访问private字段,要取消权限检查.暴力访问
            Field field1 = clazz.getDeclaredField("age");
            field1.setAccessible(true);
            field1.set(obj, 24);
            Object o1 = field1.get(obj);
            System.out.println(o1.toString()+"  "+obj.toString());
            //反射方式获取方法
            //获取某个Class对象的方法集合，主要有以下几个方法：
            //- getDeclaredMethods() 方法返回类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。
            //- getMethods() 方法返回某个类的所有公用（public）方法，包括其继承类的公用方法。
            //- getMethod方法返回一个特定的方法，其中第一个参数为方法名称，后面的参数为方法的参数对应Class的对象
            Method[] methods=clazz.getMethods();
            for(Method method:methods){
                System.out.println(method);
            }
            //只获取本类中的所有方法，包含私有
            Method[] allmethods=clazz.getDeclaredMethods();
            for(Method method:allmethods){
                System.out.println(method);
            }
            //调用方法
            Method method1=clazz.getMethod("toString",null);
            Object obj2=clazz.newInstance();
            method1.invoke(obj2,null);
            System.out.println(method1.invoke(obj2,null));
            //调用带参方法
            Method method2=clazz.getMethod("what",String.class,Integer.class);
            Object obj3=clazz.newInstance();
            method2.invoke(obj3,"小明",32);
            System.out.println( method2.invoke(obj3,"小明",32));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
