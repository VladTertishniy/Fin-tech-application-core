package com.extrawest.core.service.impl;

import com.extrawest.core.feignClient.OperatorFeignClient;
import com.extrawest.core.feignClient.SellPointFeignClient;
import com.extrawest.core.model.ApplicationForm;
import com.extrawest.core.model.Loan;
import com.extrawest.core.model.Status;
import com.extrawest.core.model.dto.request.LoanRequestDto;
import com.extrawest.core.model.dto.response.DeleteResponseDto;
import com.extrawest.core.model.dto.response.LoanResponseDto;
import com.extrawest.core.model.dto.response.OperatorResponseDto;
import com.extrawest.core.model.dto.response.SellPointResponseDto;
import com.extrawest.core.model.mapper.LoanMapper;
import com.extrawest.core.repository.ApplicationFormRepository;
import com.extrawest.core.repository.LoanRepository;
import com.extrawest.core.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

    private LoanRepository loanRepository;
    private LoanMapper loanMapper;
    private ApplicationFormRepository applicationFormRepository;
    private OperatorFeignClient operatorFeignClient;
    private SellPointFeignClient sellPointFeignClient;

    @Override
    public LoanResponseDto create(LoanRequestDto loanRequestDto) {
        Loan loan = loanMapper.toModel(loanRequestDto);
        ApplicationForm applicationForm = getApplicationForm(loanRequestDto.getApplicationFormId());
        long operatorId = loanRequestDto.getOperatorId();
        long sellPointId = loanRequestDto.getSalePointId();
        OperatorResponseDto operatorResponseDto = operatorFeignClient.getOperator(operatorId).getBody();
        SellPointResponseDto sellPointResponseDto = sellPointFeignClient.getSellPoint(loanRequestDto.getSalePointId()).getBody();
        if (operatorResponseDto != null) {
            loan.setOperatorId(operatorId);
        } else throw new NoSuchElementException("Operator with id " + operatorId + " not found");
        if (sellPointResponseDto != null) {
            loan.setSalePointId(sellPointId);
        } else throw new NoSuchElementException("Sale point with id " + sellPointId + " not found");
        loan.setApplicationForm(applicationForm);
        loan.setCreatedWhen(LocalDateTime.now());
        loan.setStatus(Status.DRAFT);
        return loanMapper.toDto(loanRepository.save(loan));
    }

    @Override
    public LoanResponseDto update(LoanRequestDto loanRequestDto, Long loanId) {
        Loan loan = loanMapper.toModel(loanRequestDto);
        loan.setId(loanId);
        loan.setApplicationForm(getApplicationForm(loanRequestDto.getApplicationFormId()));
        loan.setUpdatedWhen(LocalDateTime.now());
        return loanMapper.toDto(loanRepository.save(loan));
    }

    @Override
    public DeleteResponseDto delete(Long loanId) {
        loanRepository.deleteById(loanId);
        return new DeleteResponseDto("Loan deleted", loanId);
    }

    @Override
    public LoanResponseDto accept(Long loanId) {
        Optional<Loan> loan = loanRepository.findById(loanId);
        if (loan.isEmpty()) {
            throw new NoSuchElementException("Loan with id: " + loanId + " not found");
        }
        loan.get().setStatus(Status.SENT_TO_BANK);
        loan.get().setUpdatedWhen(LocalDateTime.now());
        loanRepository.save(loan.get());
        return null;
    }

    private ApplicationForm getApplicationForm(Long applicationFormId) {
        return applicationFormRepository
                .findById(applicationFormId)
                .orElseThrow(() -> new NoSuchElementException("Application form with id: " + applicationFormId + " not found"));
    }
}
