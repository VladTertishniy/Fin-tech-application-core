package com.extrawest.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "buyers")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ApplicationForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String lastName;
    private String surname;
    private String registrationCity;
    private String registrationAddress;
    private String passportSeries;
    private String passportNumber;
    private PassportType passportType;
    private String taxpayerCode;
    private LocalDateTime dateOfBirth;
    private Gender gender;
    private EmploymentType employmentType;
    private String email;
    private String phoneNumber;
    private boolean isWorkOfficially;
}
