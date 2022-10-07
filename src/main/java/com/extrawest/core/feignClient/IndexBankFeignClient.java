package com.extrawest.core.feignClient;

import com.extrawest.core.model.ApplicationForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "index-bank-application")
public interface IndexBankFeignClient {
    @GetMapping("/loans/sendLoan")
    ResponseEntity<String> sendLoanRequest(@RequestBody ApplicationForm applicationForm);
}
