package com.hengxunda.dfs.api;

import com.google.gson.Gson;

public abstract class GsonUtils {

    private static final Gson GSON = new Gson();

    public static <T> T parseObject(String jsonData, Class<T> type) {
        T result = GSON.fromJson(jsonData, type);
        return result;
    }
}
