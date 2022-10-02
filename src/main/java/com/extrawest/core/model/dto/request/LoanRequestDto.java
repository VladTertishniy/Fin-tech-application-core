package com.extrawest.core.model.dto.request;

import com.extrawest.core.model.SaleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.Period;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequestDto {
    private SaleType saleType;
    private long salePointId;
    private long operatorId;
    private long buyerId;
    private long applicationFormId;
    private long income;
    private long loanAmount;
    private LocalDateTime loanTermFrom;
    private LocalDateTime loanTermTo;
    private long commission;
}
