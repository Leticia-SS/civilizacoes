package org.infnet.config;

import com.google.gson.Gson;
import io.javalin.json.JsonMapper;
import org.jetbrains.annotations.NotNull;
import java.lang.reflect.Type;

import java.lang.reflect.Type;

public class GsonMapper implements JsonMapper {
    private final Gson gson = new Gson();
    @Override
    public String toJsonString(Object obj, Type type){
        return gson.toJson(obj);
    }

    @Override
    public <T> T fromJsonString(String json, Type targetType){
        return gson.fromJson(json,targetType);
    }


}
