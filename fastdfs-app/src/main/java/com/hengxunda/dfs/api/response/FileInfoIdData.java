package com.hengxunda.dfs.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfoIdData extends BaseResponse {

    private FileInfoId body;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class FileInfoId {

        public Integer fileInfoId;
    }

}
