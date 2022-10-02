package com.extrawest.core.feignClient;

import com.extrawest.core.model.dto.response.OperatorResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "persona-application")
public interface OperatorFeignClient {
    @GetMapping("/operators/getById/{id}")
    ResponseEntity<OperatorResponseDto> getOperator(@PathVariable Long id);
}
