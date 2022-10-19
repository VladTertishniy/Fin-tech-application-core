package com.extrawest.core.repository;

import com.extrawest.core.model.BankRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRequestRepository extends JpaRepository<BankRequest, Long> {
}
