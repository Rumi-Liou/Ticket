package com.example.ticket.controller.dto.requset;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
public class AddRequetBody {
    @NotBlank(message = "交易日不可為空")
    private String tradeDate;
    @NotBlank(message = "分行不可為空")
    private String branchNo;
    @NotBlank(message = "客戶帳號不可為空")
    private String custSeq;
    @NotBlank(message = "委託書號不可為空")
    private String docSeq;
    @NotBlank(message = "股票代號不可為空")
    private String stock;
    @NotBlank(message = "買進價格不可為空")
    private BigDecimal price;
    @NotBlank(message = "買進股數不可為空")
    private BigDecimal qty;
}
