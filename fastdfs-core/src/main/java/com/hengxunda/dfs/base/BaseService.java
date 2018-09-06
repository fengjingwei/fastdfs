package com.hengxunda.dfs.base;

public class BaseService extends BaseObject {

	public final String SIGN_SPLIT = "$"; // 分隔符

	public final int TIMSTAMP_ERROR_SECONDS = 2 * 60 * 60; // 客户端的发送请求时间与服务器的时间相差超过多少秒是无效的

}
