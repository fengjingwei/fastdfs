package com.hengxunda.dfs.api;

/**
 * API参数配置
 */
public class APIConfigure {

    /**
     * 应用编码
     */
    private final String appKey;
    /**
     * DFS HTTP服务器的地址<br>
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

    /**
     * 全参构造函数
     *
     * @param appKey             应用编码
     * @param httpServerUrl      DFS HTTP服务器的地址, 如:http://xxxx.com:8080/
     * @param uploadBufferSize   上传缓存大小(非必要,默认1M)
     * @param downloadBufferSize 下载缓存大小(非必要,默认1M)
     * @param coreThreadSize     http请求线程池大小(非必要,默认5)
     */
    public APIConfigure(String appKey, String httpServerUrl, int uploadBufferSize, int downloadBufferSize, int coreThreadSize) {
        this.appKey = appKey;
        this.httpServerUrl = httpServerUrl;
        this.uploadBufferSize = uploadBufferSize;
        this.downloadBufferSize = downloadBufferSize;
        this.coreThreadSize = coreThreadSize;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getHttpServerUrl() {
        return httpServerUrl;
    }

    public int getUploadBufferSize() {
        return uploadBufferSize;
    }

    /**
     * 上传缓存大小(非必要,默认1M)
     */
    public void setUploadBufferSize(int uploadBufferSize) {
        this.uploadBufferSize = uploadBufferSize;
    }

    public int getDownloadBufferSize() {
        return downloadBufferSize;
    }

    /**
     * 下载缓存大小(非必要,默认1M)
     */
    public void setDownloadBufferSize(int downloadBufferSize) {
        this.downloadBufferSize = downloadBufferSize;
    }

    public int getCoreThreadSize() {
        return coreThreadSize;
    }

    /**
     * http请求线程池大小(非必要,默认5)
     */
    public void setCoreThreadSize(int coreThreadSize) {
        this.coreThreadSize = coreThreadSize;
    }
}
