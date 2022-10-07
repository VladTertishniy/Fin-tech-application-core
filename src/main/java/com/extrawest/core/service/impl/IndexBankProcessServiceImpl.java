package com.extrawest.core.service.impl;

import com.extrawest.core.feignClient.IndexBankFeignClient;
import com.extrawest.core.model.ApplicationForm;
import com.extrawest.core.model.BankNames;
import com.extrawest.core.service.BankProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndexBankProcessServiceImpl implements BankProcessService {

    private final IndexBankFeignClient indexBankFeignClient;

    @Override
    public HttpStatus send(ApplicationForm applicationForm) {
        return indexBankFeignClient.sendLoanRequest(applicationForm).getStatusCode();
    }

    @Override
    public BankNames getBankName() {
        return BankNames.INDEX_BANK;
    }
}
