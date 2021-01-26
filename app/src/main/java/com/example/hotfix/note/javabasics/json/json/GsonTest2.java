package com.example.hotfix.note.javabasics.json.json;

import com.google.gson.Gson;

public class GsonTest2 {

    static class GsonBean {
        private int value = 1;
        private String name = "";
        private GsonBean1 gsonBean1;

        public GsonBean() {
        }

        public GsonBean(int value, String name, GsonBean1 gsonBean1) {
            this.value = value;
            this.name = name;
            this.gsonBean1 = gsonBean1;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public GsonBean1 getGsonBean1() {
            return gsonBean1;
        }

        public void setGsonBean1(GsonBean1 gsonBean1) {
            this.gsonBean1 = gsonBean1;
        }
    }

    static class GsonBean1 {
        private int value = 1;
        private String name = "";
        private GsonBean gsonBean;

        public GsonBean1() {
        }

        public GsonBean1(int value, String name, GsonBean gsonBean) {
            this.value = value;
            this.name = name;
            this.gsonBean = gsonBean;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public GsonBean getGsonBean() {
            return gsonBean;
        }

        public void setGsonBean(GsonBean gsonBean) {
            this.gsonBean = gsonBean;
        }
    }

    public static void main(String ... args){
            //TODO:
        GsonBean gsonBean = new GsonBean();
        gsonBean.setName("gsonbean");
        gsonBean.setValue(1);
        GsonBean1 gsonBean1 = new GsonBean1();
        gsonBean1.setName("gsonBean1");
        gsonBean1.setValue(2);
        gsonBean.setGsonBean1(gsonBean1);
        gsonBean1.setGsonBean(gsonBean);

        Gson gson = new Gson();
        System.out.println("gsonbean: " + gson.toJson(gsonBean,GsonBean.class));

    }
}
