package com.hengxunda.dfs.init;

import com.hengxunda.dfs.core.service.AppInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SystemInitor implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AppInfoService appInfoService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        appInfoService.loadAppInfoToCache();
        if (log.isDebugEnabled()) {
            log.debug("all appInfo loaded to cache !");
        }
    }

}
