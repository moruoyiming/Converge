package com.example.converge.note.javabasics.gson.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class GsonTest2 {
    static class Foo {
        private String s;
        private int i;

        public Foo() {
            this(null, 5);
        }

        public Foo(String s, int i) {
            this.s = s;
            this.i = i;
        }
    }

    public static void main(String... args) {
test1();
    }

    public static void test1() {
        //基本使用
        //反射的TypeAdapter 耗性能
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls().create();
        Foo foo = new Foo();
        String json = gson.toJson(foo);
        System.out.println(json);
    }

    public static void test2() {
        //自定义TypeAdapter
        Gson gson = new GsonBuilder().registerTypeAdapter(Foo.class
                , new TypeAdapter<Foo>() {

                    @Override
                    public void write(JsonWriter out, Foo value) throws IOException {
                        if (value == null) {//进行非空判断
                            out.nullValue();
                            return;
                        }
                        //把Food对象制定成你自己定义的格式的字符串进行输出：不一定是json格式了，就看你怎么组织
                        out.beginObject();
                        out.name("s").value(value.s+"sdfas");
                        out.name("i").value(value.i);
                        out.endObject();
                    }

                    @Override
                    public Foo read(JsonReader in) throws IOException {
                        if (in.peek() == JsonToken.NULL) {//进行非空判断
                            in.nextNull();
                            return null;
                        }
                        //读取json串并封装成Foo对象返回之
                        final Foo foo = new Foo();
                        in.beginObject();
                        while (in.hasNext()) {
                            switch (in.nextName()) {
                                case "s":
                                    foo.s = in.nextString();
                                    break;
                                case "i":
                                    foo.i = in.nextInt();
                                    break;
                            }
                        }
                        in.endObject();
                        return foo;
                    }
                }).create();

        Foo foo = new Foo();
        String json = gson.toJson(foo);
        System.out.println(json);

    }

    public static void test3() {
        //nullSafe()演示
        Gson gson = new GsonBuilder().registerTypeAdapter(Foo.class
                , new TypeAdapter<Foo>() {

                    @Override
                    public void write(JsonWriter out, Foo value) throws IOException {
                        out.beginObject();
                        out.name("s").value(value.s);
                        out.name("i").value(value.i);
                        out.endObject();
                    }

                    @Override
                    public Foo read(JsonReader in) throws IOException {
                        //读取json串并封装成Foo对象返回之
                        final Foo foo = new Foo();
                        in.beginObject();
                        while (in.hasNext()) {
                            switch (in.nextName()) {
                                case "s":
                                    foo.s = in.nextString();
                                    break;
                                case "i":
                                    foo.i = in.nextInt();
                                    break;
                            }
                        }
                        in.endObject();
                        return foo;
                    }
                }.nullSafe()).create();
        Foo foo = new Foo();
        String json = gson.toJson(foo);
        System.out.println(json);
    }

}
