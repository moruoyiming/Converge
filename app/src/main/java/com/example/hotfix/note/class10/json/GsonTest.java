package com.example.hotfix.note.class10.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GsonTest {

    static String CurPath = System.getProperty("user.dir");

    public static void main(String... args) throws Exception {
        //TODO:

//        test1();
        test2();
    }

    static class GsonBean {
        private String name;
        private int age;

        public GsonBean(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "GsonBean{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static void test2() throws IOException {
        //TODO: 没有数据头的纯数组的JSON
        Gson gson = new Gson();
        //1. 生成json文件
        File file = new File(CurPath + "/gson2.json");
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String line;
        StringBuffer sb = new StringBuffer();

        while (null != (line = br.readLine())) {
            sb.append(line);
        }
        fis.close();
        isr.close();
        br.close();

        System.out.println(sb.toString());

//        HomeMarqueeModel model = new Gson().fromJson(sb.toString(), HomeMarqueeModel.class);
//        System.out.println("model: " + model);


        HomeBannerModel[] array = new Gson().fromJson(sb.toString(), HomeBannerModel[].class);
        List<HomeBannerModel> list = Arrays.asList(array);
        System.out.println(list);


        Type type = new TypeToken<List<HomeBannerModel>>() {
        }.getType();
        List<HomeBannerModel> list1 = new Gson().fromJson(sb.toString(), type);
        System.out.println(list1);
        List<HomeBannerModel> list2 = getListGirl(sb.toString(),HomeBannerModel.class);
        System.out.println("fanxing: " + list2);


    }

    public static <T> List<T> getListGirl(String jsonStr, Class<T> tClass) {
        Type type = new Gson().fromJson(jsonStr, new GirlParameterizedType(tClass));
        List<T> listGirl = new Gson().fromJson(jsonStr, type);
        return listGirl;
    }

    private static class GirlParameterizedType implements ParameterizedType {
        private Class aClass;

        GirlParameterizedType(Class aClass) {
            this.aClass = aClass;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{aClass};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }

    public static <T> List<T> getListGirl(String jsonStr) {
        Type type = new TypeToken<List<T>>() {
        }.getType();
        List<T> listGirl = new Gson().fromJson(jsonStr, type);
        return listGirl;
    }

    public <T> T getObjectGirl(String jsonStr, Class<T> tGirl) {
        T girl = new Gson().fromJson(jsonStr, tGirl);
        return girl;
    }

    public static void test1() throws Exception {
        //TODO: 没有数据头的纯数组的JSON
        Gson gson = new Gson();
        //1. 生成json文件
        File file = new File(CurPath + "/gsonjsontest.json");
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String line;
        StringBuffer sb = new StringBuffer();

        while (null != (line = br.readLine())) {
            sb.append(line);
        }
        fis.close();
        isr.close();
        br.close();

        System.out.println(sb.toString());

        //Json的解析类对象
        JsonParser jsonParser = new JsonParser();
        //转化成一个JsonArray对象
        JsonArray jsonArray = jsonParser.parse(sb.toString()).getAsJsonArray();

        ArrayList<GsonBean> gsonBeans = new ArrayList<>();
        for (JsonElement jsonElement : jsonArray) {
            //使用Gson,直接转化成bean对象
            GsonBean gsonBean = gson.fromJson(jsonElement, GsonBean.class);
            gsonBeans.add(gsonBean);
        }

        System.out.println(gsonBeans);

    }
}
