package com.bsoft;

import com.bsoft.sqlServer.service.SqlServerService;
import com.bsoft.sqlServer2.service.SqlServerService2;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: lcn_louis
 * @Date: 2019/5/10 11:09 AM
 */
@Component
@EnableScheduling
@EnableAsync
public class MultithreadScheduleTask {
    private static Logger log = Logger.getLogger(MultithreadScheduleTask.class);

    @Autowired
    private SqlServerService sqlServerService;
    @Autowired
    private SqlServerService2 sqlServerService2;

    @Async
    @Scheduled(fixedDelay = 60000)  //间隔60秒
    public void task() {
        String taskData = sqlServerService.selectDate();
        String taskData2 = sqlServerService2.selectDate();
        log.info("taskData:" + taskData);
        log.info("taskData2:" + taskData2);
    }
}