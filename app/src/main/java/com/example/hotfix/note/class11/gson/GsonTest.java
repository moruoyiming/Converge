package com.example.hotfix.note.class11.gson;

import com.google.gson.Gson;

public class GsonTest {

    static class GsonBean{
        public int i;
        public String str;
    }

    //基本使用
    public static void main(String ... args){
        Gson gson = new Gson();

        System.out.println( gson.toJson(1));
        System.out.println( gson.toJson("zero"));

        int[] values = {1,2,3};
        System.out.println( gson.toJson(values));
        //
        int i = gson.fromJson("1",int.class);
        System.out.println("i: " + i);

        GsonBean gsonBean = new GsonBean();
        gsonBean.i= 2;
        gsonBean.str = "str";

        String json = gson.toJson(gsonBean);
        System.out.println("json: " + json);


        GsonBean gsonBean1 = gson.fromJson(json,GsonBean.class);
        System.out.println("gsonBean1: " + gsonBean1);

    }
}
