package com.hengxunda.dfs.core.fastdfs;

import com.hengxunda.dfs.base.spring.SpringContext;
import com.hengxunda.dfs.core.entity.FileInfoEntity;
import com.hengxunda.dfs.core.service.FileInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

@Slf4j
@AllArgsConstructor
public class DFSUploadFileTask implements Runnable {

    private FileInfoService fileInfoService = SpringContext.getBean(FileInfoService.class);

    private Integer fileInfoId; // 对应dfs_file_info的id
    private File fileToUpload;
    private InputStream in;
    private String fileExtName;
    private String groupName; // 上传到的组

    public DFSUploadFileTask(int fileInfoId, File file, String groupName) {
        this.fileInfoId = fileInfoId;
        this.fileToUpload = file;
        this.groupName = groupName;
    }

    public DFSUploadFileTask(int fileInfoId, InputStream in, String groupName, String fileExtName) {
        this.fileInfoId = fileInfoId;
        this.in = in;
        this.groupName = groupName;
        this.fileExtName = fileExtName;
    }

    @Override
    public void run() {
        if (fileInfoId != null && fileInfoId > 0) {
            String fileId = null;
            if (this.in != null) {
                try {
                    fileId = HttpClient.getInstance().uploadFile(in, groupName, fileExtName);
                } catch (Exception e) {
                    log.error("upload file error!", e);
                }
            } else if (fileToUpload != null) {
                try {
                    fileId = HttpClient.getInstance().uploadFile(fileToUpload, groupName);
                } catch (Exception e) {
                    log.error("upload file error!", e);
                }
            }

            // 上传成功执行一些保存fileId与业务数据映射关系的操作
            if (fileId != null) {
                FileInfoEntity fileInfo = new FileInfoEntity();
                fileInfo.setId(fileInfoId);
                fileInfo.setFileId(fileId);
                fileInfo.setUpdateDate(new Date());
                fileInfo.setStatus(FileInfoEntity.FILE_STATUS_UPLOADED);
                fileInfoService.updateFileInfoById(fileInfo);
            }
        } else {
            log.error("dfs_file_info id is null ,can't upload file !");
        }
    }

}
