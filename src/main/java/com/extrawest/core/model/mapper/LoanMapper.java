package com.extrawest.core.model.mapper;

import com.extrawest.core.model.Loan;
import com.extrawest.core.model.dto.request.LoanRequestDto;
import com.extrawest.core.model.dto.response.LoanResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoanMapper {
    LoanResponseDto toDto(Loan loan);

    Loan toModel(LoanRequestDto loanRequestDto);
}
