package com.fdm.pmsuibackend.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fdm.pmscommon.dto.incoming.AccountCreationDto;
import com.fdm.pmscommon.dto.outgoing.AccountDto;
import com.fdm.pmscommon.entities.Account;
import com.fdm.pmscommon.entities.User;
import com.fdm.pmscommon.repositories.AccountRepository;
import com.fdm.pmsuibackend.details.UserPrincipal;
import com.fdm.pmsuibackend.mapper.AccountMapper;

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
