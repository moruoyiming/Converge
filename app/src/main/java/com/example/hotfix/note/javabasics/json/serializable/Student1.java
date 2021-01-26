package com.example.hotfix.note.javabasics.json.serializable;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Student1 extends Person implements Serializable {

    private static final long serialVersionUID = -2100492893943893602L;
    private Integer age;

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    private List<Course> courses;

    //用transient关键字标记的成员变量不参与序列化(在被反序列化后，transient 变量的值被设为初始值，如 int 型的是 0，对象型的是 null)
    private transient Date createTime;
    //静态成员变量属于类不属于对象，所以不会参与序列化(对象序列化保存的是对象的“状态”，也就是它的成员变量，因此序列化不会关注静态变量)
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();


    public Student1(String name, String sax, Integer age) {
        super(name,sax);
        System.out.println("Student: " + name + " " + sax + " " + age);
        this.age = age;
        courses = new ArrayList<>();
        createTime = new Date();
    }

    public void newDate() {
        createTime = new Date();
    }


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student1{" +
                "age=" + age +
                ", courses=" + courses +
                "} " + super.toString();
    }

    public static void main(String ... args) throws Exception{
            //TODO:
        Student1 student = new Student1("Zero", "男", 18);
        student.addCourse(new Course("语文", 90.2f));
        student.addCourse(new Course("数学", 89.3f));
        //序列化
        byte[] bytes = SerializeableUtils.serialize(student);
        System.out.println(Arrays.toString(bytes));

        //反序列化
        //在readObject时抛出java.io.NotSerializableException异常。
        //需要Person添加一个无参数构造器
        Student1 student1 = SerializeableUtils.deserialize(bytes);
        student1.newDate();
        System.out.println("Student: " + student1);
    }
}
