package com.kalashnyk.denys.qrscanner.repository.database.converters;

import android.arch.persistence.room.TypeConverter;
import android.text.TextUtils;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class Converters {

    @TypeConverter
    public List<String> fromJson(String value) {
        Gson gson = new Gson();
        if (value == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(value, listType);
        //return value != null && !TextUtils.isEmpty(value) ? new Gson().fromJson(value, (Type) String.class) : null;
    }

    @TypeConverter
    public String toJson(List<String> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

}
