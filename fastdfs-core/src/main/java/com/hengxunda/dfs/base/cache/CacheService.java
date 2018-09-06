package com.hengxunda.dfs.base.cache;

import com.hengxunda.dfs.core.entity.AppInfoEntity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheService {

    /**
     * 缓存应用信息，做定时刷新，添加删除时注间实现更新缓存
     */
    public final static Map<String, AppInfoEntity> APP_INFO_CACHE = new ConcurrentHashMap<>();
}
