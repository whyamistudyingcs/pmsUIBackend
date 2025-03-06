package com.fdm.pmsuibackend.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fdm.pmscommon.dto.general.TradeDto;
import com.fdm.pmscommon.dto.incoming.TradeHistoryRequestDto;
import com.fdm.pmscommon.dto.outgoing.TradeHistoryResponseDto;
import com.fdm.pmscommon.entities.Account;
import com.fdm.pmscommon.entities.Trade;
import com.fdm.pmscommon.entities.User;
import com.fdm.pmscommon.repositories.AccountRepository;
import com.fdm.pmscommon.repositories.TradeRepository;
import com.fdm.pmsuibackend.details.UserPrincipal;
import com.fdm.pmsuibackend.mapper.TradeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TradeQueryService {
    private final TradeRepository tradeRepository;
    private final AccountRepository accountRepository;
    private final TradeMapper tradeMapper;

    public TradeHistoryResponseDto getTradeHistory(TradeHistoryRequestDto tradeHistoryRequestDto) {
        
        Account account = accountRepository.findById(tradeHistoryRequestDto.getAccountId()).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Account not found"
        ));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User curUser = ((UserPrincipal) auth.getPrincipal()).getUser();

        if (!account.getOwner().getId().equals(curUser.getId())) {
            throw new ResponseStatusException(
                HttpStatus.FORBIDDEN,
                "You are not authorized to view this account trading history"
            );
        }

        List<Trade> trade = tradeRepository.findByTradeDateBetweenAndAccountId(
            tradeHistoryRequestDto.getStartDate(),
            tradeHistoryRequestDto.getEndDate(),
            account.getId()
        );

        TradeHistoryResponseDto tradeHistoryResponseDto = new TradeHistoryResponseDto();
        tradeHistoryResponseDto.setTrades(trade.stream()
            .map(tradeMapper::toDto)
            .collect(Collectors.toList()));
        tradeHistoryResponseDto.setStartDate(tradeHistoryRequestDto.getStartDate());
        tradeHistoryResponseDto.setEndDate(tradeHistoryRequestDto.getEndDate());
        return tradeHistoryResponseDto;
    }

    public TradeDto getTradeById(UUID tradeId) {
        Trade trade = tradeRepository.findById(tradeId).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Trade not found"
        ));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User curUser = ((UserPrincipal) auth.getPrincipal()).getUser();

        if (!trade.getAccount().getOwner().getId().equals(curUser.getId())) {
            throw new ResponseStatusException(
                HttpStatus.FORBIDDEN,
                "You are not authorized to view this trade"
            );
        }

        return tradeMapper.toDto(trade);
    }

    public TradeDto getTradeByExternalId(String externalId) {
        Trade trade = tradeRepository.findByExternalOrderId(externalId);
        if (trade == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Trade not found"
            );
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User curUser = ((UserPrincipal) auth.getPrincipal()).getUser();

        if (!trade.getAccount().getOwner().getId().equals(curUser.getId())) {
            throw new ResponseStatusException(
                HttpStatus.FORBIDDEN,
                "You are not authorized to view this trade"
            );
        }
        
        return tradeMapper.toDto(trade);
    }
}
