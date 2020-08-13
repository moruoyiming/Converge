package com.example.hotfix.note.class14.ex1;

/**
 * @author King老师
 * 栈帧执行对内存区域的影响
 */
public class Person {

    public  int work()throws Exception{//运行过程中 打包一个栈帧
        int x =1;//x是一个局部变量
        int y =2;
        int z =(x+y)*10;
        return  z;
    }
    public static void main(String[] args) throws Exception{
        Person person = new Person();//person 一个引用， new Person(）对象
        person.work();//执行完了，出栈
        person.hashCode();
        int i =12;
    }
}
