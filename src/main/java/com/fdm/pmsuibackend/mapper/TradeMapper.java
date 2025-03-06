package com.fdm.pmsuibackend.mapper;

import org.springframework.stereotype.Component;

import com.fdm.pmscommon.dto.general.TradeDto;
import com.fdm.pmscommon.entities.Trade;

import lombok.Data;

@Data
@Component
public class TradeMapper {
    public TradeDto toDto(Trade trade) {
        if (trade == null) {
            return null;
        }

        TradeDto dto = new TradeDto();
        dto.setTradeId(trade.getOrderId());
        dto.setExtOrderId(trade.getExternalOrderId());
        dto.setTradeDate(trade.getTradeDate());
        dto.setTicker(trade.getTicker());
        dto.setOrderType(trade.getOrderType());
        dto.setQuantity(trade.getQuantity());
        dto.setExchange(trade.getExchange());
        dto.setPrice(trade.getPrice());
        dto.setCurrency(trade.getCurrency());
        dto.setPositionId(trade.getPositionId() != null ? trade.getPositionId() : null);
        dto.setAccountId(trade.getAccount() != null ? trade.getAccount().getId() : null);
        
        return dto;
    }
}
