package com.extrawest.core.schedule;

import com.extrawest.core.util.PathUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Properties;

@Getter
@Setter
public class SendingBankRequestTask extends AbstractTask {
    private static final Long DEFAULT_DELAY = 20000L;

    public SendingBankRequestTask(long bankRequestId, long applicationFormId) {
        setTaskId("Task for bank request: " + bankRequestId);
        setStartDate(new Date(new Date().getTime() + DEFAULT_DELAY));
        properties.put(PathUtil.BANK_REQUEST_ID_KEY, bankRequestId);
        properties.put(PathUtil.APPLICATION_FORM_ID_KEY, applicationFormId);
    }

    @Override
    public String getTaskId() {
        return taskId;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.SENDING_BANK_REQUEST_TASK;
    }

    @Override
    public Properties getProperties() {
        return properties;
    }
}
