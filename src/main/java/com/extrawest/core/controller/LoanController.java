package com.extrawest.core.controller;

import com.extrawest.core.model.dto.request.LoanRequestDto;
import com.extrawest.core.model.dto.response.DeleteResponseDto;
import com.extrawest.core.model.dto.response.LoanResponseDto;
import com.extrawest.core.service.LoanService;
import com.extrawest.core.util.PathUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(PathUtil.LOANS_ROOT_PATH)
@AllArgsConstructor
public class LoanController {

    private LoanService loanService;

    @PostMapping(PathUtil.CREATE_PATH)
    public ResponseEntity<LoanResponseDto> create(@RequestBody @Valid LoanRequestDto loanRequestDto) {
        return ResponseEntity.ok(loanService.create(loanRequestDto));
    }

    @PostMapping(PathUtil.UPDATE_PATH)
    public ResponseEntity<LoanResponseDto> update(@RequestBody @Valid LoanRequestDto loanRequestDto,
                                                  @PathVariable Long id) {
        return ResponseEntity.ok(loanService.update(loanRequestDto, id));
    }

    @GetMapping(PathUtil.DELETE_PATH)
    public ResponseEntity<DeleteResponseDto> delete(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.delete(id));
    }

    @GetMapping(PathUtil.ACCEPT_PATH)
    public ResponseEntity<LoanResponseDto> accept(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.accept(id));
    }

}
