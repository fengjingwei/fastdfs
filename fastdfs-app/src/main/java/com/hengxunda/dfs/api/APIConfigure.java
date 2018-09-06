package com.hengxunda.dfs.api;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * API参数配置
 */
@Data
@AllArgsConstructor
public class APIConfigure {

    /**
     * 应用编码
     */
    private final String appKey;
    /**
     * DFS HTTP服务器的地址
     * http://xxxx.com:8080/
     */
    private final String httpServerUrl;

    /**
     * 上传缓存大小(非必要,默认1M)
     */
    private int uploadBufferSize;

    /**
     * 下载缓存大小(非必要,默认1M)
     */
    private int downloadBufferSize;

    /**
     * http请求线程池大小(非必要,默认5)
     */
    private int coreThreadSize;

    /**
     * 必要参数构造函数
     *
     * @param appKey        应用编码
     * @param httpServerUrl DFS HTTP服务器的地址, 如:http://xxxx.com:8080/
     */
    public APIConfigure(String appKey, String httpServerUrl) {
        this.appKey = appKey;
        this.httpServerUrl = httpServerUrl;
    }
}
