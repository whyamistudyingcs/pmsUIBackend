package com.fdm.pmsuibackend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.pmscommon.dto.general.TradeDto;
import com.fdm.pmscommon.dto.incoming.TradeHistoryRequestDto;
import com.fdm.pmscommon.dto.outgoing.TradeHistoryResponseDto;
import com.fdm.pmsuibackend.service.TradeQueryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/trade-queries")
@RequiredArgsConstructor
public class TradeQueryController {

    private final TradeQueryService tradeQueryService;

    @GetMapping
    public ResponseEntity<TradeHistoryResponseDto> findByDateBetweenAndAccount(@Valid @RequestBody TradeHistoryRequestDto tradeHistoryRequestDto) {
        return ResponseEntity.ok(tradeQueryService.getTradeHistory(tradeHistoryRequestDto));
    }

    @GetMapping("/id/{tradeId}")
    public ResponseEntity<TradeDto> findByTradeId(@PathVariable UUID tradeId) {
        return ResponseEntity.ok(tradeQueryService.getTradeById(tradeId));
    }

    @GetMapping("/external-id/{externalTradeId}")
    public ResponseEntity<TradeDto> findByExternalTradeId(@PathVariable String externalTradeId) {
        return ResponseEntity.ok(tradeQueryService.getTradeByExternalId(externalTradeId));
    }
    
}
