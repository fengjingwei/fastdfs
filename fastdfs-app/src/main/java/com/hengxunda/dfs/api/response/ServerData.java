package com.hengxunda.dfs.api.response;

import com.hengxunda.dfs.api.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServerData extends BaseResponse {

    private ServerDataBody body;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ServerDataBody {

        /**
         * tracker服务器可以是多个,如:10.0.11.201:22122,10.0.11.202:22122,10.0.11.203:22122
         */
        private String trackerServers;

        /**
         * 当前appKey对应的组
         */
        private String groupName;
    }
}
