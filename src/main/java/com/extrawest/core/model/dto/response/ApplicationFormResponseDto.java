package com.extrawest.core.model.dto.response;

import com.extrawest.core.model.EmploymentType;
import com.extrawest.core.model.Gender;
import com.extrawest.core.model.PassportType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationFormResponseDto {
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
