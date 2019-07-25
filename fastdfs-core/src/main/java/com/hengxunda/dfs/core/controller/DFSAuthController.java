package com.hengxunda.dfs.core.controller;

import com.hengxunda.dfs.base.BaseController;
import com.hengxunda.dfs.base.ErrorCodeEnum;
import com.hengxunda.dfs.base.cache.CacheService;
import com.hengxunda.dfs.core.entity.AppInfoEntity;
import com.hengxunda.dfs.core.entity.FileInfoEntity;
import com.hengxunda.dfs.core.fastdfs.HttpClient;
import com.hengxunda.dfs.core.service.FileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;

@Slf4j
@RestController
@RequestMapping(value = "/dfs/auth")
public class DFSAuthController extends BaseController {

    @Autowired
    private FileInfoService fileInfoService;

    @RequestMapping(value = "/v1/upload/self", method = RequestMethod.POST)
    public String uploadToSelf(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        int fileInfoId;
        try {
            String fileName = file.getOriginalFilename();
            String appKey = request.getHeader(HEADER_APP_KEY);
            fileInfoId = fileInfoService.addFileInfo(appKey, FileInfoEntity.FILE_ACCESS_TYPE_BELONGS_AUTH, fileName, file.getSize());
            AppInfoEntity appInfo = CacheService.APP_INFO_CACHE.get(appKey);
            BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
            HttpClient.getInstance().executeUploadTask(fileInfoId, bis, appInfo.getGroupName(), FilenameUtils.getExtension(fileName));
        } catch (Exception e) {
            log.error("upload file error!", e);
            return getResponseByCode(ErrorCodeEnum.SERVER_ERROR);
        }
        if (fileInfoId > 0) {
            String body = "{\"id\":" + fileInfoId + "}";
            return getResponseOKWithBody(body);
        } else {
            return getResponseByCode(ErrorCodeEnum.SERVER_ERROR);
        }
    }

}
