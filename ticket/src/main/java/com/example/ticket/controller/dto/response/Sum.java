package com.example.ticket.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sum {

   private String stock;
   private String stockName;
   private BigDecimal nowPrice;
   private BigDecimal sumRemainQty= BigDecimal.valueOf(0);
   private BigDecimal sumFee=BigDecimal.valueOf(0);
   private BigDecimal sumCost=BigDecimal.valueOf(0);
   private BigDecimal sumMarketValue=BigDecimal.valueOf(0);
   private BigDecimal sumUnrealProfit=BigDecimal.valueOf(0);
   private List<Detail> detaiList;

}
