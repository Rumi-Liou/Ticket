package com.example.ticket.model.Entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="mstmb")
public class Mstmb {
    @Id
    @Column(name="Stock")
    private String stock;
    @Column(name="StockName")
    private String stockName;
    @Column(name="MarketType")
    private String marketType;
    //市場別(T-上市 O-上櫃 R-興櫃)
    @Column(name="CurPrice")
    private BigDecimal curPrice;
    @Column(name="RefPrice")
    private BigDecimal RefPrice;
    @Column(name="Currency")
    private String currency;
    @Column(name="ModDate")
    private String modDate;
    @Column(name="ModTime")
    private String modTime;
    @Column(name="ModUser")
    private String modUser;
}
