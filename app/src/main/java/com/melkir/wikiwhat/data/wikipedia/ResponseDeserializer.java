package com.melkir.wikiwhat.data.wikipedia;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class ResponseDeserializer<T> implements JsonDeserializer<T> {
    private Class<T> mClass;
    private String mKey;

    public ResponseDeserializer(Class<T> mClass, String mKey) {
        this.mClass = mClass;
        this.mKey = mKey;
    }

    @Override
    public T deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {
        JsonElement content = json.getAsJsonObject().get(mKey);
        return new Gson().fromJson(content, mClass);
    }
}
