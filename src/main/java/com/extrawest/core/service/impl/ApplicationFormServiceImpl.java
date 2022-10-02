package com.extrawest.core.service.impl;

import com.extrawest.core.model.ApplicationForm;
import com.extrawest.core.model.dto.request.ApplicationFormRequestDto;
import com.extrawest.core.model.dto.response.ApplicationFormResponseDto;
import com.extrawest.core.model.dto.response.DeleteResponseDto;
import com.extrawest.core.model.mapper.ApplicationFormMapper;
import com.extrawest.core.repository.ApplicationFormRepository;
import com.extrawest.core.service.ApplicationFormService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ApplicationFormServiceImpl implements ApplicationFormService {

    private ApplicationFormMapper applicationFormMapper;
    private ApplicationFormRepository applicationFormRepository;

    @Override
    public ApplicationFormResponseDto create(ApplicationFormRequestDto applicationFormRequestDto) {
        ApplicationForm applicationForm = applicationFormMapper.toModel(applicationFormRequestDto);
        return applicationFormMapper.toDto(applicationFormRepository.save(applicationForm));
    }

    @Override
    public ApplicationFormResponseDto update(ApplicationFormRequestDto applicationFormRequestDto, Long applicationFormId) {
        if(applicationFormRepository.findById(applicationFormId).isEmpty()) {
            throw new NoSuchElementException("ApplicationForm with id: " + applicationFormId + " not found");
        }
        ApplicationForm applicationForm = applicationFormMapper.toModel(applicationFormRequestDto);
        applicationForm.setId(applicationFormId);
        return applicationFormMapper.toDto(applicationFormRepository.save(applicationForm));
    }

    @Override
    public DeleteResponseDto delete(Long applicationFormId) {
        applicationFormRepository.deleteById(applicationFormId);
        return new DeleteResponseDto("ApplicationForm deleted", applicationFormId);
    }
}
