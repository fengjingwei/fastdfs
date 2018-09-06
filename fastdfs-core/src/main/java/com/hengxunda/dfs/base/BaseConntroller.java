package com.hengxunda.dfs.base;

public class BaseConntroller extends BaseObject {

    public final String HEADER_APP_KEY = "dfs-request-app-key"; // 应用appKey
    public final String HEADER_TIMESTAMP = "dfs-request-timestamp"; // 时间戳
    public final String HEADER_SIGN = "dfs-request-sign"; // 签名

    public final String UTF8_JSON = "application/json;charset=utf-8";

    /**
     * 响应OK
     *
     * @return
     */
    public String getResponseOK() {
        return "{\"result\":" + ErrorCode.OK.getCode() + "}";
    }

    /**
     * 响应指定错误码
     *
     * @return
     */
    public String getResponseByCode(ErrorCode eCode) {
        return "{\"result\":" + eCode.getCode() + ",\"msg\":\"" + eCode.getMessage() + "\"}";
    }

    /**
     * 带body的OK响应
     *
     * @param body josn格式的响应
     * @return
     */
    public String getResponseOKWithBody(String body) {
        return "{\"result\":" + ErrorCode.OK.getCode() + ",\"body\":" + body + "}";
    }

}
