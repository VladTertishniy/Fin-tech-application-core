package com.extrawest.core.service.impl;

import com.extrawest.core.exception.ApiRequestException;
import com.extrawest.core.exception.ExceptionMessage;
import com.extrawest.core.feignClient.OperatorFeignClient;
import com.extrawest.core.feignClient.SellPointFeignClient;
import com.extrawest.core.model.*;
import com.extrawest.core.model.dto.request.LoanRequestDto;
import com.extrawest.core.model.dto.response.DeleteResponseDto;
import com.extrawest.core.model.dto.response.LoanResponseDto;
import com.extrawest.core.model.dto.response.OperatorResponseDto;
import com.extrawest.core.model.dto.response.SellPointResponseDto;
import com.extrawest.core.model.mapper.LoanMapper;
import com.extrawest.core.repository.ApplicationFormRepository;
import com.extrawest.core.repository.BankRequestRepository;
import com.extrawest.core.repository.LoanBankRequestRepository;
import com.extrawest.core.repository.LoanRepository;
import com.extrawest.core.schedule.SendingBankRequestTask;
import com.extrawest.core.service.BankProcessService;
import com.extrawest.core.service.LoanService;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

    private LoanRepository loanRepository;
    private LoanMapper loanMapper;
    private ApplicationFormRepository applicationFormRepository;
    private OperatorFeignClient operatorFeignClient;
    private SellPointFeignClient sellPointFeignClient;
    private LoanBankRequestRepository loanBankRequestRepository;
    private BankRequestRepository bankRequestRepository;

    private SchedulerServiceImpl schedulerService;

    @Autowired
    private List<BankProcessService> bankProcessServiceList;

    @Override
    public LoanResponseDto create(LoanRequestDto loanRequestDto) {
        long operatorId = loanRequestDto.getOperatorId();
        long sellPointId = loanRequestDto.getSalePointId();
        OperatorResponseDto operatorResponseDto = operatorFeignClient.getOperator(operatorId).getBody();
        SellPointResponseDto sellPointResponseDto = sellPointFeignClient.getSellPoint(loanRequestDto.getSalePointId()).getBody();
        if (!onCreateUpdateValidation(loanRequestDto, operatorResponseDto)) throw new ApiRequestException("Bad loan request");
        Loan loan = loanMapper.toModel(loanRequestDto);
        ApplicationForm applicationForm = getApplicationForm(loanRequestDto.getApplicationFormId());
        loan.setOperatorId(operatorId);
        if (sellPointResponseDto != null) {
            loan.setSalePointId(sellPointId);
        } else throw new NoSuchElementException("Sale point with id " + sellPointId + " not found");
        loan.setApplicationForm(applicationForm);
        loan.setCreatedWhen(LocalDateTime.now());
        loan.setLoanStatus(LoanStatus.DRAFT);
        return loanMapper.toDto(loanRepository.save(loan));
    }

    @Override
    public LoanResponseDto update(LoanRequestDto loanRequestDto, Long loanId) {
        long operatorId = loanRequestDto.getOperatorId();
        OperatorResponseDto operatorResponseDto = operatorFeignClient.getOperator(operatorId).getBody();
        if (!onCreateUpdateValidation(loanRequestDto, operatorResponseDto)) throw new ApiRequestException("Bad loan request");
        Loan loan = loanMapper.toModel(loanRequestDto);
        loan.setId(loanId);
        loan.setApplicationForm(getApplicationForm(loanRequestDto.getApplicationFormId()));
        loan.setUpdatedWhen(LocalDateTime.now());
        loan.setSalePointId(loanRequestDto.getSalePointId());
        return loanMapper.toDto(loanRepository.save(loan));
    }

    @Override
    public DeleteResponseDto delete(Long loanId) {
        loanRepository.deleteById(loanId);
        return new DeleteResponseDto("Loan deleted", loanId);
    }

    @Override
    public LoanResponseDto accept(Long loanId) {
        Loan loan = getLoanById(loanId);
        try {
            for (BankProcessService service: bankProcessServiceList) {
                BankRequest bankRequest = new BankRequest();
                bankRequest.setStatus(BankRequestStatus.AWAITING_SEND);
                bankRequest.setDateOfDispatch(new Date(System.currentTimeMillis() + 1200L));
                bankRequest.setBankName(service.getBankName());
                bankRequestRepository.save(bankRequest);
                SendingBankRequestTask task = new SendingBankRequestTask(bankRequest.getId(), loan.getApplicationForm().getId());
                schedulerService.addTask(task);
                saveLoanBankRequest(loan, service.getBankName());
            }
        } catch (FeignException e) {
            throw new ApiRequestException(ExceptionMessage.BAD_FEIGN_REQUEST);
        }

        loan.setLoanStatus(LoanStatus.SENT_TO_BANK);
        loan.setUpdatedWhen(LocalDateTime.now());
        return loanMapper.toDto(loanRepository.save(loan));
    }

    private void saveLoanBankRequest(Loan loan, BankNames bankName) {
        LoanBankRequest loanBankRequest = new LoanBankRequest();
        loanBankRequest.setLoan(loan);
        loanBankRequest.setBankName(bankName);
        loanBankRequest.setLoanAmount(loan.getLoanAmount());
        loanBankRequest.setIncome(loan.getIncome());
        loanBankRequest.setOperatorId(loan.getOperatorId());
        loanBankRequest.setSalePointId(loan.getSalePointId());
        loanBankRequestRepository.save(loanBankRequest);
    }

    private ApplicationForm getApplicationForm(Long applicationFormId) {
        return applicationFormRepository
                .findById(applicationFormId)
                .orElseThrow(() -> new NoSuchElementException(
                        "Application form with id: " + applicationFormId + " not found")
                );
    }

    private Loan getLoanById(long id) {
        return loanRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Loan with id: " + id + " not found")
        );
    }

    private boolean onCreateUpdateValidation(LoanRequestDto loanRequestDto, OperatorResponseDto operatorResponseDto) {
        if (operatorResponseDto == null) return false;
        if (!(loanRequestDto.getOperatorId() == operatorResponseDto.getId())) return false;
        if (!(loanRequestDto.getSalePointId() == operatorResponseDto.getSellPointIdentifier())) return false;
        return true;
    }
}
