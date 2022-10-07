package com.extrawest.core.repository;

import com.extrawest.core.model.LoanBankRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanBankRequestRepository extends JpaRepository<LoanBankRequest, Long> {
}
