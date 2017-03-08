package com.melkir.wikiwhat.data.wikipedia.util;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class ItemDeserializer<T> implements JsonDeserializer<T> {
    private Class<T> mClass;
    private String mKey;

    public ItemDeserializer(Class<T> mClass, String mKey) {
        this.mClass = mClass;
        this.mKey = mKey;
    }

    @Override
    public T deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {
        JsonElement content = json
                .getAsJsonObject().get("query")
                .getAsJsonObject().getAsJsonArray(mKey).get(0);
        return new Gson().fromJson(content, mClass);
    }
}