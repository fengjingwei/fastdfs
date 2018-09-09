package com.hengxunda.dfs.core.fastdfs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.OutputStream;

/**
 * 下载任务，异步执行
 */
@Data
@AllArgsConstructor
public class DFSDownloadFileTask implements Runnable {

    private String fileId;

    private OutputStream out;

    private boolean isClose;

    @Override
    public void run() {
    }

}
