package com.extrawest.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "bankRequests")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BankRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private BankNames bankName;
    private Date dateOfDispatch;
    private BankRequestStatus status;
}
