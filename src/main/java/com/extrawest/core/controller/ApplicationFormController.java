package com.extrawest.core.controller;

import com.extrawest.core.model.dto.request.ApplicationFormRequestDto;
import com.extrawest.core.model.dto.response.ApplicationFormResponseDto;
import com.extrawest.core.model.dto.response.DeleteResponseDto;
import com.extrawest.core.service.ApplicationFormService;
import com.extrawest.core.util.PathUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(PathUtil.APPLICATION_FORMS_ROOT_PATH)
@AllArgsConstructor
public class ApplicationFormController {

    private ApplicationFormService applicationFormService;

    @PostMapping(PathUtil.CREATE_PATH)
    public ResponseEntity<ApplicationFormResponseDto> create(@RequestBody @Valid ApplicationFormRequestDto applicationFormRequestDto) {
        return ResponseEntity.ok(applicationFormService.create(applicationFormRequestDto));
    }

    @PostMapping(PathUtil.DELETE_PATH)
    public ResponseEntity<DeleteResponseDto> delete(@PathVariable Long id) {
        return ResponseEntity.ok(applicationFormService.delete(id));
    }

    @PostMapping(PathUtil.UPDATE_PATH)
    public ResponseEntity<ApplicationFormResponseDto> update(@RequestBody @Valid ApplicationFormRequestDto applicationFormRequestDto,
                                                             @PathVariable Long id) {
        return ResponseEntity.ok(applicationFormService.update(applicationFormRequestDto, id));
    }
}
