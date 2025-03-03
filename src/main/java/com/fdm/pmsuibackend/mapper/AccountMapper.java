package com.fdm.pmsuibackend.mapper;

import org.springframework.stereotype.Component;

import com.fdm.pmscommon.dto.outgoing.AccountDto;
import com.fdm.pmscommon.entities.Account;

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
