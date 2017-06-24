package com.example.shayla.wetleak;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * This class uses Gson to convert the Json into objects and is not written by me and all
 * credit is completely attributed to Oum Saokosal, the author of KosalGeek on 9/6/15.
 *
 * @author Oum Saokosal
 */
public class JsonConverter<T> {

    public ArrayList<T> toArrayList(String jsonString, Class<T> clazz){
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("dd/MM/yy HH:mm:ss");
        Gson gson = builder.create();
        Type type = new ListParameterizedType(clazz);
        ArrayList<T> list = gson.fromJson(jsonString, type);

        return list;
    }

    public List<T> toList(String jsonString, Class<T> clazz) {

        List<T> list = toArrayList(jsonString, clazz);

        return list;
    }

    private static class ListParameterizedType implements ParameterizedType {

        private Type type;

        private ListParameterizedType(Type type) {
            this.type = type;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[] {type};
        }

        @Override
        public Type getRawType() {
            return ArrayList.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }

    }
}