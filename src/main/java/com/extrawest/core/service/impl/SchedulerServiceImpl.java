package com.extrawest.core.service.impl;

import com.extrawest.core.schedule.Task;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class SchedulerServiceImpl {
    private final ThreadPoolTaskScheduler scheduler;

    public void addTask(Task task) {
        scheduler.schedule(task, task.getStartDate());
        log.info(String.format("Task successfully added. Task will be run at %s%n", task.getStartDate()));
    }
}
