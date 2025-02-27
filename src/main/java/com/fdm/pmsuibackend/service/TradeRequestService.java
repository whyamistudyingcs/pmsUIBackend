package com.fdm.pmsuibackend.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fdm.pmsuibackend.dto.requests.TradeRequestDto;

@Service
public class TradeRequestService {

    public String createTradeRequestDto(UUID accountId, TradeRequestDto tradeRequestDto) {
        return "Trade request created for account: " + accountId + " with external trade ID: " + tradeRequestDto.getExternalTradeId();
    }
}
