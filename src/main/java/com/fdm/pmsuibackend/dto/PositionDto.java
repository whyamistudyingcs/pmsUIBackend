package com.fdm.pmsuibackend.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author April Chou
 * @Classname PositionData
 * @Description TODO
 * @Version 1.0
 * @Date 2025/1/23 22:19
 */
//
//PositionID
//
//        Symbol
//
//Qty
//
//        AverageCost
//
//UnrealizedPnL
//
//        Currency
//
//CurrentPrice
//    get a list
//    ticker?
@Component
@Data
public class PositionDto {
    private String positionId;
    private String ticker;
    private String totalQty;
    private String avgPrice;
    private String unrealizedPnl;
    private String currency;
    private String lastPrice;
}
