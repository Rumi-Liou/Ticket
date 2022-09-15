package com.example.ticket.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Detail {
    private String tradeDate;
    private String docSeq;
    private String stock;
    private String stockName;
    private BigDecimal buyPrice;
    private BigDecimal nowPrice;
    private BigDecimal qty;
    private BigDecimal remainQty;
    private BigDecimal fee;
    private BigDecimal cost;
    private BigDecimal marketValue;
    private BigDecimal unrealProfit;
}
