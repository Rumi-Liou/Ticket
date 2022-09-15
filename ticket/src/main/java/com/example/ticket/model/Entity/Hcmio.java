package com.example.ticket.model.Entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(Compositekey.class)
@Entity
@Table(name="hcmio")
public class Hcmio {

    @Id
    @Column(name="TradeDate",nullable = false)
    private String tradeDate;
    @Id
    @Column(name="BranchNo",nullable = false)
    private String branchNo;
    @Id
    @Column(name="CustSeq",nullable = false)
    private String custSeq;
    @Id
    @Column(name="DocSeq",nullable = false)
    private String docSeq;
    @Column(name="Stock")
    private String stock;
    @Column(name="BsType")
    private String bsType;
    @Column(name="Price")
    private BigDecimal price;
    @Column(name="Qty")
    private BigDecimal qty;
    @Column(name="Amt")
    private BigDecimal amt;
    @Column(name="Fee")
    private BigDecimal fee;
    @Column(name="Tax")
    private BigDecimal tax;
    @Column(name="StinTax")
    private BigDecimal stinTax;
    @Column(name="NetAmt")
    private BigDecimal netAmt;
    @Column(name="ModDate")
    private String modDate;
    @Column(name="ModTime")
    private String modTime;
    @Column(name="ModUser")
    private String modUser;
}
