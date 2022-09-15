package com.example.ticket.model.Entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Getter
@Setter
@Entity
@IdClass(Compositekey.class)
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tcund")
public class Tcund {

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
    @Column(name="DocSeq")
    private String docSeq="AB001";
    @Column(name="Stock")
    private String stock;
    @Column(name="Price")
    private BigDecimal price;
    @Column(name="Qty")
    private BigDecimal qty;
    @Column(name="RemainQty")
    private BigDecimal remainQty;
    @Column(name="Fee")
    private BigDecimal fee;
    @Column(name="Cost")
    private BigDecimal cost;
    @Column(name="ModDate")
    private String modDate;
    @Column(name="modTime")
    private String modTime;
    @Column(name="modUser")
    private String modUser;
   /* public double getAmt(){
        double amtPrice;
        amtPrice=getPrice()*getQty();
        return amtPrice;
    }
    public double getFee(){
        double feePrice;
        feePrice=(getAmt()*1425)/100000;
        return Math.round(feePrice);
    }
    public double getTax(){
        double TaxPrice;
        TaxPrice=(getAmt()*3)/1000;
        return  Math.round(TaxPrice);
    }
  public double getStinTax(){
      return  0;
  }
    public String getModDate(){
        LocalDate c=LocalDate.now();
        modDate=c.toString();
        return  modDate;
    }
    public String getModeTime(){
        String time;
        int now;
        Calendar cal=Calendar.getInstance();
        now= cal.get(Calendar.HOUR_OF_DAY)+cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND);
        time=Integer.toString(now);
        return time;
    }*/
}
