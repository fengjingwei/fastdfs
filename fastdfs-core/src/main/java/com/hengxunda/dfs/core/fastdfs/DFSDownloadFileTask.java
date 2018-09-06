package com.hengxunda.dfs.core.fastdfs;

import lombok.AllArgsConstructor;

import java.io.OutputStream;

/**
 * 下载任务，异步执行
 */
@AllArgsConstructor
public class DFSDownloadFileTask implements Runnable {

    private String fileId;

    private OutputStream out;

    private boolean isClose;

    @Override
    public void run() {
    }

}
