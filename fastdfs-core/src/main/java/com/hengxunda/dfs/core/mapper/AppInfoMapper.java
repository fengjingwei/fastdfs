package com.hengxunda.dfs.core.mapper;

import com.hengxunda.dfs.core.entity.AppInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 应用mapper
 */
@Mapper
public interface AppInfoMapper {

    /**
     * 查询出所有应用信息
     */
    List<AppInfoEntity> getAllAppInfo();

    /**
     * 查询出指定应用信息
     */
    AppInfoEntity getAppInfoByAppKey(@Param("appKey") String appKey);
}
