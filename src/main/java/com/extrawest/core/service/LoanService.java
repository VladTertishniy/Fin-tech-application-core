package com.extrawest.core.service;

import com.extrawest.core.model.dto.request.LoanRequestDto;
import com.extrawest.core.model.dto.response.DeleteResponseDto;
import com.extrawest.core.model.dto.response.LoanResponseDto;

public interface LoanService {
    LoanResponseDto create(LoanRequestDto loanRequestDto);
    LoanResponseDto update(LoanRequestDto loanRequestDto, Long loanId);
    DeleteResponseDto delete(Long loanId);
    LoanResponseDto accept(Long loanId);
}
