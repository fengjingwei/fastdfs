package com.hengxunda.dfs.core.service;

import com.hengxunda.dfs.base.BaseErrorCode;
import com.hengxunda.dfs.base.cache.CacheService;
import com.hengxunda.dfs.core.entity.AppInfoEntity;
import com.hengxunda.dfs.core.mapper.AppInfoMapper;
import com.hengxunda.dfs.utils.DateUtils;
import com.hengxunda.dfs.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.StringJoiner;

/**
 * 应用服务类
 */
@Slf4j
@Service
public class AppInfoService {

    @Autowired
    private AppInfoMapper appInfoMapper;

    private static final int TIMSTAMP_ERROR_SECONDS = 2 * 60 * 60; // 客户端的发送请求时间与服务器的时间相差超过多少秒是无效的

    /**
     * 将应用信息载入缓存
     *
     * @return
     */
    public void loadAppInfoToCache() {
        List<AppInfoEntity> lstApps = appInfoMapper.getAllAppInfo();
        if (lstApps != null && !lstApps.isEmpty()) {
            for (AppInfoEntity app : lstApps) {
                CacheService.APP_INFO_CACHE.put(app.getAppKey(), app);
            }
        } else {
            CacheService.APP_INFO_CACHE.clear();
        }
    }

    /**
     * 获取应用
     *
     * @param appKey
     * @return
     */
    public AppInfoEntity getAppInfo(String appKey) {
        if (appKey == null) {
            return null;
        }
        AppInfoEntity app = CacheService.APP_INFO_CACHE.get(appKey);
        if (app == null) {
            app = appInfoMapper.getAppInfoByAppKey(appKey);
            if (app != null) {
                CacheService.APP_INFO_CACHE.put(app.getAppKey(), app);
            }
        }
        return app;
    }

    /**
     * 应用信息校验
     *
     * @param appKey    应用编码
     * @param timestamp 时间戳
     * @param sign      MD5(appKey + '$' + appSecret + '$' + 时间戳)
     * @return BaseErrorCode
     */
    public BaseErrorCode checkAuth(String appKey, String timestamp, String sign) {
        BaseErrorCode result = BaseErrorCode.OK;
        AppInfoEntity app = getAppInfo(appKey);

        if (app == null) {
            log.warn("app info not found! appKey:{}", appKey);
            return BaseErrorCode.APP_NOT_EXIST;
        }

        if (app.getStatus() == null || app.getStatus().intValue() != AppInfoEntity.APP_STATUS_OK) {
            log.warn("app stopped ! appKey:{}", appKey);
            return BaseErrorCode.APP_STOPPED; // 应用已停用
        }

        StringJoiner joiner = new StringJoiner("$");
        joiner.add(appKey).add(app.getAppSecret()).add(timestamp);
        String md5Sign = MD5Utils.md5(joiner.toString());
        if (!sign.equalsIgnoreCase(md5Sign)) {
            log.warn("sign check error ! appKey:{}, expect:{},but get:{}", appKey, md5Sign, sign);
            return BaseErrorCode.APP_AUTH_FAILURE; // 签名校验失败
        }

        int timestampCheck = DateUtils.getSecondsToNow(timestamp);
        if (timestampCheck < 0 || timestampCheck > TIMSTAMP_ERROR_SECONDS) {
            log.warn("timestamp error ! appKey:{}, timstamp:{}, timestampCheck:{}", appKey, timestamp,
                    timestampCheck);
            return BaseErrorCode.TIMESTAMP_ERROR;
        }
        return result;
    }
}
