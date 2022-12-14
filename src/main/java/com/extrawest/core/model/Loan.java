package com.extrawest.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "loans")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime createdWhen;
    private LocalDateTime updatedWhen;
    private LoanStatus loanStatus;
    @Enumerated(EnumType.STRING)
    private SaleType saleType;
    private long salePointId;
    private long operatorId;
    @ManyToOne
    @JoinColumn(name = "application_form_id", nullable = false)
    private ApplicationForm applicationForm;
    private long income;
    private long loanAmount;
    private LocalDateTime loanTermFrom;
    private LocalDateTime loanTermTo;
    private double commission;
}
