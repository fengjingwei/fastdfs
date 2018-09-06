package com.hengxunda.dfs.base;

import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
    /**
     * 创建日期
     */
    protected Date createDate;

    /**
     * 修改日期
     */
    protected Date updateDate;
}
