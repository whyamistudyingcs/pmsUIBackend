package com.fdm.pmsuibackend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.pmscommon.dto.requests.AccountCreationDto;
import com.fdm.pmscommon.dto.responses.AccountDto;
import com.fdm.pmsuibackend.service.AccountService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody AccountCreationDto accountCreationDto) {
        return ResponseEntity.ok(accountService.createAccount(accountCreationDto));
    }
    
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable UUID accountId) {
        return ResponseEntity.ok(accountService.getAccountById(accountId));
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccountsByUser() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }
    
}
