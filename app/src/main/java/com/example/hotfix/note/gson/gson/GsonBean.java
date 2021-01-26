package com.example.hotfix.note.gson.gson;

import java.util.List;

public class GsonBean {

//    {
//        "key": "value",
//            "simpleArray": [1,2,3],
//        "arrays": [
//    [{
//        "arrInnerClsKey": "arrInnerClsValue",
//                "arrInnerClsKeyNub": 1
//    }]
//  ],
//        "innerclass": {
//        "name": null,
//                "age": 25,
//                "sax": "男"
//    }
//    }
    private String key;
    private InnerclassBean innerclass;
    private List<Integer> simpleArray;
    private List<List<ArraysBean>> arrays;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public InnerclassBean getInnerclass() {
        return innerclass;
    }

    public void setInnerclass(InnerclassBean innerclass) {
        this.innerclass = innerclass;
    }

    public List<Integer> getSimpleArray() {
        return simpleArray;
    }

    public void setSimpleArray(List<Integer> simpleArray) {
        this.simpleArray = simpleArray;
    }

    public List<List<ArraysBean>> getArrays() {
        return arrays;
    }

    public void setArrays(List<List<ArraysBean>> arrays) {
        this.arrays = arrays;
    }

    public static class InnerclassBean {

        private String name;
        private int age;
        private String sax;

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

        public String getSax() {
            return sax;
        }

        public void setSax(String sax) {
            this.sax = sax;
        }
    }

    public static class ArraysBean {

        private String arrInnerClsKey;
        private int arrInnerClsKeyNub;

        public String getArrInnerClsKey() {
            return arrInnerClsKey;
        }

        public void setArrInnerClsKey(String arrInnerClsKey) {
            this.arrInnerClsKey = arrInnerClsKey;
        }

        public int getArrInnerClsKeyNub() {
            return arrInnerClsKeyNub;
        }

        public void setArrInnerClsKeyNub(int arrInnerClsKeyNub) {
            this.arrInnerClsKeyNub = arrInnerClsKeyNub;
        }
    }
}
