package com.example.basics.string;


import com.example.basics.reflection.Person;

public class what {

    public static String change(String s) {
        s = "what";
        return s;

    }

    public static void change2(StringBuffer s, int x, Person person) {
        s.append("what");
        x = 4;
        person.setCountry("japen");
        person = new Person("ZHANGSAN",12);
        System.out.println(person.toString());
    }

    public static void main(String[] args) {
        String s = "aaa";
        String s2 = new String("aaa");
        int x=10;
        StringBuffer sb = new StringBuffer("whats");
        Person person=new Person("LISI",23);
        change2(sb,x,person);
        change(s);
        change(s2);
        System.out.println(s);
        System.out.println(s2);
        System.out.println(x);
        System.out.println(sb.toString());
        System.out.println(person.toString());
//        Singleton.getSingleton().what();

//        String url="http://131312312312313/{userCode}/{fuck}/sdfsfasdfsd/{scxx}";
//        String [] what=url.split("\\{");
//        System.out.println(what[1]+"   "+what[2]+  "   "+what[3]);
//        List<String> list=new ArrayList<>();
//        for(String s:what){
//            if(s.contains("}")){
//                String[] ss=s.split("\\}");
//                System.out.println(ss[0]);
//                list.add(ss[0])  ;
//            }
//        }
//        for(String s:list){
//            System.out.println(s.equals("userCode"));
//            url.replace(s,)
//        }
//        System.out.println(list.size()+"");

//
//        String s1=new String("aaa");
//        String s2=new String("aaa");
//        System.out.println("s"+s1.equals(s2)+"---"+ (s1==s2));
//
        String s3="aaa";
        System.out.println("s"+s.equals(s3)+ "---"+(s==s3));
//        String str1 = "ab"+ "cd";  //1个对象
//
//        String str2 = "abcd";
//
//        System.out.println("str1= str2 : "+ (str1 == str2)+"   "+str1.equals(str2));

//        List<Object> objectList=new ArrayList<>();
//        List<String> stringList=new ArrayList<>();
//        objectList = stringList;  //compilation error incompatible types
    }


}
