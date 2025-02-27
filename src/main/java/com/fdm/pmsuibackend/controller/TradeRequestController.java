package com.fdm.pmsuibackend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.pmsuibackend.dto.requests.TradeRequestDto;
import com.fdm.pmsuibackend.service.TradeRequestService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/trade-request")
@RequiredArgsConstructor
public class TradeRequestController {

    private final TradeRequestService tradeRequestService;

    @PostMapping("/{accountId}")
    public String createTradeRequest(@PathVariable UUID accountId, @Valid @RequestBody TradeRequestDto tradeRequestDto) {
        return tradeRequestService.createTradeRequestDto(accountId, tradeRequestDto); 
    }
    
}
