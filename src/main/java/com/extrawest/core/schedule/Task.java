package com.extrawest.core.schedule;

import java.util.Date;
import java.util.Properties;

public interface Task extends Runnable {
    String getTaskId();
    Date getStartDate();
    TaskType getTaskType();
    Properties getProperties();
}
