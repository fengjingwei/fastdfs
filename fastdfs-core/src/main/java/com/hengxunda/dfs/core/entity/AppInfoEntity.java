package com.hengxunda.dfs.core.entity;

import com.hengxunda.dfs.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 第三方应用信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppInfoEntity extends BaseEntity implements Serializable {

    /**
     * 启用
     */
    public static final int APP_STATUS_OK = 1;

    /**
     * 停用
     */
    public static final int APP_STATUS_STOP = 2;

    /**
     * 应用唯一编码
     */
    private String appKey;

    /**
     * 应用密钥
     */
    private String appSecret;

    /**
     * 可以上传的组编号与fastdfs的组名对应
     */
    private String groupName;

    /**
     * 状态 1:启用,2:停用
     */
    private Integer status;

    /**
     * 创建者
     */
    private Integer createBy;
}
