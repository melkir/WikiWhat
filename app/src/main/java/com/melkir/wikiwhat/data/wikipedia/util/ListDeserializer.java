package com.melkir.wikiwhat.data.wikipedia.util;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class ListDeserializer<T> implements JsonDeserializer<T> {
    private Type mType;
    private String mKey;

    public ListDeserializer(Type type, String key) {
        this.mType = type;
        this.mKey = key;
    }

    @Override
    public T deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {
        JsonElement content = json
                .getAsJsonObject().get("query")
                .getAsJsonObject().getAsJsonArray(mKey);
        return new Gson().fromJson(content, mType);
    }
}
