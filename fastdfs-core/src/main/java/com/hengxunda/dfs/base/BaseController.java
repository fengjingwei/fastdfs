package com.hengxunda.dfs.base;

public abstract class BaseController {

    public final String HEADER_APP_KEY = "dfs-request-app-key"; // 应用appKey
    public final String HEADER_TIMESTAMP = "dfs-request-timestamp"; // 时间戳
    public final String HEADER_SIGN = "dfs-request-sign"; // 签名

    public String getResponseOK() {
        return "{\"result\":" + BaseErrorCode.OK.getCode() + "}";
    }

    public String getResponseByCode(BaseErrorCode code) {
        return "{\"result\":" + code.getCode() + ",\"msg\":\"" + code.getMessage() + "\"}";
    }

    public String getResponseOKWithBody(String body) {
        return "{\"result\":" + BaseErrorCode.OK.getCode() + ",\"body\":" + body + "}";
    }
}
