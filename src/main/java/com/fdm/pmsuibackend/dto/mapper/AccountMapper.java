package com.fdm.pmsuibackend.dto.mapper;

import org.springframework.stereotype.Component;

import com.fdm.pmsuibackend.dto.responses.AccountDto;
import com.fdm.pmsuibackend.model.Account;

import lombok.Data;

@Data
@Component
public class AccountMapper {
    public AccountDto mapToAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setAccountName(account.getAccountName());
        accountDto.setDepartmentName(account.getDepartmentName());
        accountDto.setOwnerId(account.getOwner().getId());
        return accountDto;
    }
}
