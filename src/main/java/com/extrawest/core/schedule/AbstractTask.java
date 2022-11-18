package com.extrawest.core.schedule;

import com.extrawest.core.util.SpringContext;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import java.util.Date;
import java.util.Properties;

@Setter
@Slf4j
public abstract class AbstractTask implements Task {
    protected String taskId;
    protected Date startDate;
    protected TaskType taskType;
    protected Properties properties = new Properties();

    @Override
    @Async
    public void run() {
        log.info(String.format("Task execution started with ID = %s", getTaskId()));
        var executor = (TaskExecutor) SpringContext.getBean(getTaskType().getExecutorType());
        executor.execute(getProperties());
        log.info(String.format("Task execution ended with ID = %s", getTaskId()));
    }
}
