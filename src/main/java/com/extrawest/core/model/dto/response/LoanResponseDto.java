package com.extrawest.core.model.dto.response;

import com.extrawest.core.model.ApplicationForm;
import com.extrawest.core.model.LoanStatus;
import com.extrawest.core.model.SaleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanResponseDto {
    private long id;
    private LocalDateTime createdWhen;
    private LocalDateTime updatedWhen;
    private LoanStatus loanStatus;
    private SaleType saleType;
    private Long salePointId;
    private Long operatorId;
    private ApplicationForm applicationForm;
    private long income;
    private long loanAmount;
    private LocalDateTime loanTermFrom;
    private LocalDateTime loanTermTo;
    private double commission;
}
