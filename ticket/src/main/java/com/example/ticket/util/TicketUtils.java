package com.example.ticket.util;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class TicketUtils {

    public static BigDecimal getUnrealProfit(BigDecimal marketValue, BigDecimal cost) {
        return marketValue.subtract(cost)
                .setScale(0, RoundingMode.HALF_UP);
    }

    public static BigDecimal getMarketValue(BigDecimal amt, BigDecimal fee, BigDecimal tax) {
        return amt.subtract(fee.add(tax))
                .setScale(0, RoundingMode.HALF_UP);
    }

    public static BigDecimal getTax(BigDecimal amt) {
        return amt.multiply(BigDecimal.valueOf(0.003))
                .setScale(0, RoundingMode.HALF_UP);
    }

    public static BigDecimal getTax(BigDecimal price, BigDecimal qty) {
        return getAmt(price, qty)
                .multiply(BigDecimal.valueOf(0.003))
                .setScale(0, RoundingMode.HALF_UP);
    }

    public static BigDecimal getFee(BigDecimal price, BigDecimal qty) {
        return getAmt(price, qty)
                .multiply(BigDecimal.valueOf(0.001425))
                .setScale(0, RoundingMode.HALF_UP);
    }

    public static BigDecimal getFee(BigDecimal amt) {
        return amt
                .multiply(BigDecimal.valueOf(0.001425))
                .setScale(0, RoundingMode.HALF_UP);
    }

    public static double getStinTax() {
        return 0;
    }

    public static String getModDate() {
        return LocalDate.now().toString();
    }

    public static String getModeTime() {
        return LocalTime.now().toString();
    }


    public static BigDecimal getAmt(BigDecimal price, BigDecimal qty) {
        return price.multiply(qty)
                .setScale(0, RoundingMode.HALF_UP);
    }
}
