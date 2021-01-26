package com.example.hotfix.note.javabasics.gson.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class GsonError1 {


    /**
     * name : java
     * authors :
     */

    private String name;
    private List<AuthorsBean> authors;

    @Override
    public String toString() {
        return "GsonError1{" +
                "name='" + name + '\'' +
                ", authors=" + authors +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AuthorsBean> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorsBean> authors) {
        this.authors = authors;
    }

    public static class AuthorsBean {
        /**
         * id : 1'
         * name : Joshua Bloch'
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "AuthorsBean{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    static class GsonError1Deserializer implements JsonDeserializer<GsonError1> {

        @Override
        public GsonError1 deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            final JsonObject jsonObject = json.getAsJsonObject();
            final JsonElement jsonTitle = jsonObject.get("name");
            final String name = jsonTitle.getAsString();

            JsonElement jsonAuthors = jsonObject.get("authors");

            GsonError1 gsonError1 = new GsonError1();

            if (jsonAuthors.isJsonArray()) {//如果数组类型，此种情况是我们需要的
                //关于context在文章最后有简单说明
                AuthorsBean[] authors = context.deserialize(jsonAuthors, AuthorsBean[].class);
                gsonError1.setAuthors(Arrays.asList(authors));
            } else {//此种情况为无效情况
                gsonError1.setAuthors(null);
            }
            gsonError1.setName(name);
            return gsonError1;
        }
    }

    static class AuthorDeserializer implements JsonDeserializer {

        @Override
        public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            final JsonObject jsonObject = json.getAsJsonObject();

            final AuthorsBean author = new AuthorsBean();
            author.setId(jsonObject.get("id").getAsString());
            author.setName(jsonObject.get("name").getAsString());
            return author;
        }
    }

    public static void main(String... args) {
//        test1();
//        test2();
        test3();
    }

    public static void test1() {
        //TODO:
        String json = "{\n" +
                "    \"name\": \"java\",\n" +
                "    \"authors\": [\n" +
                "        {\n" +
                "            \"id\": \"1'\",\n" +
                "            \"name\": \"Joshua Bloch'\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"2'\",\n" +
                "            \"name\": \"Tom\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        Gson gson = new Gson();
        GsonError1 gsonError1 = gson.fromJson(json, GsonError1.class);

        System.out.println(gsonError1);

    }


    public static void test2() {
        //TODO:
        String json = "{\n" +
                "    \"name\": \"java\",\n" +
                "    \"authors\": \"\"\n" +
                "}";
        Gson gson = new Gson();
        GsonError1 gsonError1 = gson.fromJson(json, GsonError1.class);

        System.out.println(gsonError1);
    }

    public static void test3() {
        //TODO:
        String json = "{\n" +
                "    \"name\": \"java\",\n" +
                "    \"authors\": \"\"\n" +
                "}";

        GsonBuilder gsonBuilder = new GsonBuilder();

        //注册TypeAdapter
        gsonBuilder.registerTypeAdapter(GsonError1.class, new GsonError1Deserializer());
        gsonBuilder.registerTypeAdapter(AuthorsBean.class, new AuthorDeserializer());

        Gson gson = gsonBuilder.create();
        GsonError1 gsonError1 = gson.fromJson(json, GsonError1.class);

        System.out.println(gsonError1);
    }
}
