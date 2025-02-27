package com.fdm.pmsuibackend.dto.requests;

import com.fdm.pmsuibackend.dto.validator.NonZero;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/*
 * TradeRequestDto will be propagated to the TradeService to create a new trade.
 */
@Data
public class TradeRequestDto {
    @NotBlank(message = "external ID is mandatory")
    private String externalTradeId;

    @NotBlank(message = "Trade symbol is mandatory")
    private String symbol;

    @NotNull(message = "Trade quantity is mandatory")
    @NonZero(message = "Trade quantity cannot be zero")
    private Integer quantity;

    @NotNull(message = "Trade price is mandatory")
    @Positive(message = "Trade price must be positive")
    private Double price;

    @NotBlank(message = "Trade type is mandatory")
    private String tradeType;

    @NotBlank(message = "Trade exchange is mandatory")
    private String exchange;

    @NotBlank(message = "Trade currency is mandatory")
    private String currency;
}
