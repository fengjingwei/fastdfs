package com.hengxunda.dfs.api;

import lombok.Data;

@Data
public class BaseResponse<T> {

    private int result;

    private T body;
}
