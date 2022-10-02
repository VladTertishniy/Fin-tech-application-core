package com.extrawest.core.model.mapper;

import com.extrawest.core.model.ApplicationForm;
import com.extrawest.core.model.dto.request.ApplicationFormRequestDto;
import com.extrawest.core.model.dto.response.ApplicationFormResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicationFormMapper {
    ApplicationFormResponseDto toDto(ApplicationForm applicationForm);

    ApplicationForm toModel(ApplicationFormRequestDto apartmentRequestDto);
}
