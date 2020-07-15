package com.example.hotfix.note.class10.myjson.model;

import com.zero.serializabledemo.myjson.JsonUtils;
import com.zero.serializabledemo.myjson.exception.JsonTypeException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class JsonArray implements Iterable {

    private List list = new ArrayList();

    public void add(Object obj) {
        list.add(obj);
    }

    public Object get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public JsonObject getJsonObject(int index) {
        Object obj = list.get(index);
        if (!(obj instanceof JsonObject)) {
            throw new JsonTypeException("Type of value is not JsonObject");
        }

        return (JsonObject) obj;
    }

    public JsonArray getJsonArray(int index) {
        Object obj = list.get(index);
        if (!(obj instanceof JsonArray)) {
            throw new JsonTypeException("Type of value is not JsonArray");
        }

        return (JsonArray) obj;
    }

    @Override
    public String toString() {
        return JsonUtils.beautify(this);
    }

    public Iterator iterator() {
        return list.iterator();
    }
}
