package com.extrawest.core.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TaskType {

    SENDING_BANK_REQUEST_TASK(SendingBankRequestTaskExecutor.class);

    private final Class<?> executorType;
}
