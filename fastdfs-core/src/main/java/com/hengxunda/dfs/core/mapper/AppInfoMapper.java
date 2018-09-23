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
     * 查询所有应用信息
     *
     * @return
     */
    List<AppInfoEntity> getAllAppInfo();

    /**
     * 查询指定应用信息
     *
     * @param appKey
     * @return
     */
    AppInfoEntity getAppInfoByAppKey(@Param("appKey") String appKey);
}
