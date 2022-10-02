package com.extrawest.core.repository;

import com.extrawest.core.model.ApplicationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationFormRepository extends JpaRepository<ApplicationForm, Long> {
}
