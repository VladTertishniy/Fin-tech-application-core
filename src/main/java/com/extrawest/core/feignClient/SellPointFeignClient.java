package com.extrawest.core.feignClient;

import com.extrawest.core.model.dto.response.SellPointResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "sell-point-application")
public interface SellPointFeignClient {
    @GetMapping("/sellPoints/getById/{id}")
    ResponseEntity<SellPointResponseDto> getSellPoint(@PathVariable Long id);
}
