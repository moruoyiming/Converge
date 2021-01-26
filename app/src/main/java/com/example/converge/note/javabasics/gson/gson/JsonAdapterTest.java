package com.example.converge.note.javabasics.gson.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

class MyClass {
    @SerializedName("name") //a => name
            String a;
    @SerializedName(value = "name1", alternate = {"name2", "name3"})
    String b;

    String c;

    public MyClass(String a, String b, String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void test(){
        MyClass target = new MyClass("v1", "v2", "v3");
        Gson gson = new Gson();
        String json = gson.toJson(target);
        System.out.println(json);
    }
}


class User0 {
    @Expose
    private String firstName;//参与序列化(JAVA对象转为JSON)/反序列化（JSON转为JAVA对象）

    @Expose(serialize = false)
    private String lastName;//参与反序列化

    @Expose(serialize = false, deserialize = true)
    private String emailAddress; //不参与

    private String password;//不参与
}

class User1 {
    private String firstName;//一直参与
    private String lastName;//一直参与
    @Since(1.0) private String emailAddress; //当前版本>=1.0时才会参与序列化/反序列化，否则忽略
    @Since(1.1) private String password;//当前版本>=1.0时才会参与序列化/反序列化，否则忽略
}

class User2 {
    private String firstName;//一直参与
    private String lastName;//一直参与
    @Until(1.1) private String emailAddress;//当前版本<=1.1时参加序列化/反序列化
    @Until(1.1) private String password;//当前版本<=1.1时参加序列化/反序列化
}

@JsonAdapter(UserJsonAdapter.class)
 class User {
    public final String firstName, lastName;
    User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

class UserJsonAdapter extends TypeAdapter<User> {
    @Override
    public void write(JsonWriter out, User user) throws IOException {
        out.beginObject();
        out.name("name");
        out.value(user.firstName + " " + user.lastName);
        out.endObject();
    }
    @Override
    public User read(JsonReader in) throws IOException {
        in.beginObject();
        in.nextName();
        String[] nameParts = in.nextString().split(" ");
        in.endObject();
        return new User(nameParts[0], nameParts[1]);
    }
}

public class JsonAdapterTest {

    public static void main(String[] args) {
        MyClass myClass = new MyClass("a","b","c");
        myClass.test();

        Gson gson = new GsonBuilder()
                .setVersion(1.2).create();
        User user = new User("gg","Zero");
        String gsonStr = gson.toJson(user);
        System.out.println("gsonStr=" + gsonStr);

        user = gson.fromJson(gsonStr,User.class);
        System.out.println("user: " + user);

    }
}
