package com.extrawest.core.schedule;

import com.extrawest.core.exception.ApiRequestException;
import com.extrawest.core.exception.ExceptionMessage;
import com.extrawest.core.model.ApplicationForm;
import com.extrawest.core.model.BankRequest;
import com.extrawest.core.model.BankRequestStatus;
import com.extrawest.core.repository.ApplicationFormRepository;
import com.extrawest.core.repository.BankRequestRepository;
import com.extrawest.core.service.BankProcessService;
import com.extrawest.core.util.PathUtil;
import com.extrawest.core.util.SpringContext;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@AllArgsConstructor
public class SendingBankRequestTaskExecutor implements TaskExecutor{

    private BankRequestRepository bankRequestRepository;
    private ApplicationFormRepository applicationFormRepository;

    @Override
    public void execute(Properties properties) {
        long bankRequestId = (long) properties.get(PathUtil.BANK_REQUEST_ID_KEY);
        long applicationFormId = (long) properties.get(PathUtil.APPLICATION_FORM_ID_KEY);
        BankRequest bankRequest = bankRequestRepository.findById(bankRequestId).get();
        ApplicationForm applicationForm = applicationFormRepository.findById(applicationFormId).get();
        BankProcessService service = (BankProcessService)SpringContext.getBeanByQualifier(bankRequest.getBankName().getBankName(), BankProcessService.class);

        HttpStatus status = service.send(applicationForm);
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
