package com.extrawest.core.model;

import com.extrawest.core.exception.ApiRequestException;
import com.extrawest.core.exception.ExceptionMessage;
import com.extrawest.core.repository.BankRequestRepository;
import com.extrawest.core.service.BankProcessService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
public class SendingBankRequestTask implements Runnable {
    private BankProcessService bankProcessService;
    private BankRequestRepository bankRequestRepository;
    private Date startDate;
    private BankRequest bankRequest;
    private ApplicationForm applicationForm;

    public SendingBankRequestTask(BankProcessService bankProcessService,
                                  Date startDate,
                                  BankRequest bankRequest,
                                  ApplicationForm applicationForm,
                                  BankRequestRepository bankRequestRepository) {
        this.bankProcessService = bankProcessService;
        this.startDate = startDate;
        this.bankRequest = bankRequest;
        this.applicationForm = applicationForm;
        this.bankRequestRepository = bankRequestRepository;
    }

    @Override
    public void run() {
        HttpStatus status = bankProcessService.send(applicationForm);
        if (!status.is2xxSuccessful()) {
            bankRequest.setStatus(BankRequestStatus.ERROR);
            bankRequestRepository.save(bankRequest);
            throw new ApiRequestException(ExceptionMessage.BANK_DONT_ACCEPT_REQUEST);
        } else {
            bankRequest.setStatus(BankRequestStatus.SENT);
            bankRequestRepository.save(bankRequest);
        }
    }
}
