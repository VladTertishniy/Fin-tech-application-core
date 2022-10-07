package com.extrawest.core.service;

import com.extrawest.core.model.ApplicationForm;
import com.extrawest.core.model.BankNames;
import org.springframework.http.HttpStatus;

public interface BankProcessService {
    HttpStatus send(ApplicationForm applicationForm);
    BankNames getBankName();
}
