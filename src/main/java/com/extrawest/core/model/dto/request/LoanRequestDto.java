package com.extrawest.core.model.dto.request;

import com.extrawest.core.model.SaleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequestDto {
    private SaleType saleType;
    @Positive
    private long salePointId;
    @Positive
    private long operatorId;
    @Positive
    private long applicationFormId;
    @Positive
    private long income;
    @Positive
    private long loanAmount;
    @FutureOrPresent
    private LocalDateTime loanTermFrom;
    @Future
    private LocalDateTime loanTermTo;
    @Positive
    private double commission;
}
