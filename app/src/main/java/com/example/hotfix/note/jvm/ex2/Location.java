package com.example.hotfix.note.jvm.ex2;

public class Location {

    private String city;
    private String region;

    public static void main(String[] args) {
        //JVM首先会检查该对象是否在字符串常量池中，如果在，就返回该对象引用，否则新的字符串将在常量池中被创建。
        //这种方式可以减少同一个值的字符串对象的重复创建，节约内存。
        String str ="abc";


        //首先在编译类文件时，"abc"常量字符串将会放入到常量结构中，在类加载时，“abc"将会在常量池中创建；
        //其次，在调用 new 时，JVM 命令将会调用 String 的构造函数，同时引用常量池中的"abc” 字符串，
        // 在堆内存中创建一个 String 对象；最后，str 将引用 String 对象。
        String str1 =new String("abc");

        Location location = new Location();
        location.setCity("深圳");
        location.setRegion("南山");

        //首先会生成 ab 对象，再生成 abcd 对象，最后生成 abcdef 对象
        String str2= "ab" + "cd" + "ef";


        //new Sting() 会在堆内存中创建一个a的String对象，
        // “king"将会在常量池中创建
        // 在调用intern方法之后，会去常量池中查找是否有等于该字符串对象的引用，有就返回引用。
        String a =new String("king").intern();
        //调用 new Sting() 会在堆内存中创建一个b的String 对象，。
        //在调用 intern 方法之后，会去常量池中查找是否有等于该字符串对象的引用，有就返回引用。
        String b = new String("king").intern();
        //所以 a 和 b 引用的是同一个对象。
        if(a==b) {
            System.out.print("a==b");
        }else{
            System.out.print("a!=b");
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
