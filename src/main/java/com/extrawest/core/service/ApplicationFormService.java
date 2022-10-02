package com.extrawest.core.service;

import com.extrawest.core.model.dto.request.ApplicationFormRequestDto;
import com.extrawest.core.model.dto.response.ApplicationFormResponseDto;
import com.extrawest.core.model.dto.response.DeleteResponseDto;

public interface ApplicationFormService {
    ApplicationFormResponseDto create(ApplicationFormRequestDto applicationFormRequestDto);
    ApplicationFormResponseDto update(ApplicationFormRequestDto applicationFormRequestDto, Long applicationFormId);
    DeleteResponseDto delete(Long applicationFormId);
}
