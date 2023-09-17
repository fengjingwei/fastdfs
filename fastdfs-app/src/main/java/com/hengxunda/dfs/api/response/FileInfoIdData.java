package com.hengxunda.dfs.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfoIdData {

    private FileInfoId body;

    private int result;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FileInfoId {

        public Integer fileInfoId;
    }
}
