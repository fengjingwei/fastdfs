package com.hengxunda.dfs.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SystemDestroy implements ApplicationListener<ContextStoppedEvent> {

    @Override
    public void onApplicationEvent(ContextStoppedEvent event) {
        log.debug("contexted stopped !");
    }

}
