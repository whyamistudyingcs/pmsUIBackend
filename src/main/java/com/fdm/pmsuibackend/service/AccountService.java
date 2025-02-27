package com.fdm.pmsuibackend.service;

import com.fdm.pmsuibackend.details.UserPrincipal;

import java.util.List;
import java.util.UUID;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fdm.pmsuibackend.dto.mapper.AccountMapper;
import com.fdm.pmsuibackend.dto.requests.AccountCreationDto;
import com.fdm.pmsuibackend.dto.responses.AccountDto;
import com.fdm.pmsuibackend.model.User;
import com.fdm.pmsuibackend.model.Account;
import com.fdm.pmsuibackend.repositories.AccountRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepo;
    private final AccountMapper accountMapper;

    @Transactional
    public AccountDto createAccount(AccountCreationDto accountCreationDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User curUser = ((UserPrincipal) auth.getPrincipal()).getUser();

        Account account = Account.builder()
            .accountName(accountCreationDto.getAccountName())
            .departmentName(accountCreationDto.getDepartmentName())
            .owner(curUser)
            .build();

        return accountMapper.mapToAccountDto(accountRepo.save(account));
    }

    public AccountDto getAccountById(UUID accountId) {
        Account account = accountRepo.findById(accountId).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Account not found"
        ));
        return accountMapper.mapToAccountDto(account);
    }

    public List<AccountDto> getAllAccounts() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User curUser = ((UserPrincipal) auth.getPrincipal()).getUser();

        List<Account> accounts = accountRepo.findByOwner(curUser);
        return accounts.stream().map(accountMapper::mapToAccountDto).collect(Collectors.toList());
    }
    
}
