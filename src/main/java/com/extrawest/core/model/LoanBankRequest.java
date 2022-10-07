package com.extrawest.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "loanRequests")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoanBankRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long salePointId;
    private long operatorId;
    private long income;
    private long loanAmount;
    private long LoanId;
    private BankNames bankName;

}
