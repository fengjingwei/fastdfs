package com.hengxunda.dfs.base.cache;

import com.hengxunda.dfs.core.entity.AppInfoEntity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheService {

    public final static Map<String, AppInfoEntity> APP_INFO_CACHE = new ConcurrentHashMap<>();
}
