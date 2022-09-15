package com.example.ticket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.example.ticket.util.TicketUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketTest {

    @Test
    public void testBuyAmt() {
        // 價金 = 交易股數 × 股票單價
        BigDecimal price = BigDecimal.valueOf(33.45d);
        BigDecimal qty = BigDecimal.valueOf(1000);
        BigDecimal buyAmt = getAmt(price, qty);
        assertEquals(BigDecimal.valueOf(33450), buyAmt);
    }

    @Test
    public void testBuyAmtFee() {
        // 手續費 = 價金 × 手續費費率（手續費通用費率：0.1425%）
        BigDecimal price = BigDecimal.valueOf(33.45d);
        BigDecimal qty = BigDecimal.valueOf(1000);
        BigDecimal buyAmt = getAmt(price, qty);
        BigDecimal buyAmtFee = getFee(buyAmt);
        assertEquals(BigDecimal.valueOf(48), buyAmtFee);
    }

    @Test
    public void testSellAmt() {
        // 價金 = 交易股數 × 股票單價
        BigDecimal price = BigDecimal.valueOf(35);
        BigDecimal qty = BigDecimal.valueOf(1000);
        BigDecimal sellAmt = getAmt(price, qty);
        assertEquals(BigDecimal.valueOf(35000), sellAmt);
    }

    @Test
    public void testSellAmtFee() {
        // 手續費 = 價金 × 手續費費率（手續費通用費率：0.1425%）
        BigDecimal price = BigDecimal.valueOf(35);
        BigDecimal qty = BigDecimal.valueOf(1000);
        BigDecimal sellAmt = getAmt(price, qty);
        BigDecimal sellAmtFee = getFee(sellAmt);
        assertEquals(BigDecimal.valueOf(50), sellAmtFee);
    }

    @Test
    public void testTax() {
        // 交易稅 = 價金 × 交易稅稅率（交易稅通用稅率：0.3%
        BigDecimal price = BigDecimal.valueOf(35);
        BigDecimal qty = BigDecimal.valueOf(1000);
        BigDecimal sellAmt = getAmt(price, qty);
        BigDecimal sellTax = getTax(sellAmt);
        assertEquals(BigDecimal.valueOf(105), sellTax);
    }

    @Test
    public void testMarketValue() {
        // 股票市值 = (現價 * 股數) - 賣出手續費 - 交易稅

        BigDecimal price = BigDecimal.valueOf(35);
        BigDecimal qty = BigDecimal.valueOf(1000);
        BigDecimal sellAmt = getAmt(price, qty);
        BigDecimal sellAmtFee = getFee(sellAmt); // 賣出手續費
        BigDecimal sellTax = getTax(sellAmt); // 交易稅

        BigDecimal marketValue = getMarketValue(sellAmt, sellAmtFee, sellTax); // 股票市值
        assertEquals(BigDecimal.valueOf(34845), marketValue);
    }

    @Test
    public void testUnrealProfit() {
        // 未實現損益 = 股票市值（現值） - 買進成本
        BigDecimal marketValue = BigDecimal.valueOf(34845); // 股票市值
        BigDecimal cost = BigDecimal.valueOf(33498); // 買進成本
        BigDecimal unrealProfit = getUnrealProfit(marketValue, cost);
        assertEquals(BigDecimal.valueOf(1347), unrealProfit);
    }

    @Test
    public void testTypeBUnrealProfit() {
//現股交易淨收付計算公式：
//買進淨收付（-） = 買進價金 + 買進手續費
//賣出淨收付（+） = 賣出價金 - 賣出手續費 - 賣出交易稅
//未實現損益 = 預估收入 - 買進成本 = 賣出淨收付 - 買進淨收付

//        買進淨收付（-） = 買進價金 + 買進手續費
        BigDecimal buyAmt = BigDecimal.valueOf(33450); // 買進價金
        BigDecimal buyAmtFee = BigDecimal.valueOf(48); // 買進手續費
        BigDecimal buyNetAmt = buyAmt.add(buyAmtFee); // 買進淨收付

//        賣出淨收付（+） = 賣出價金 - 賣出手續費 - 賣出交易稅
        BigDecimal sellAmt = BigDecimal.valueOf(35000); // 賣出價金
        BigDecimal sellAmtFee = BigDecimal.valueOf(50); // 賣出手續費
        BigDecimal sellTax = BigDecimal.valueOf(105); // 賣出交易稅
        BigDecimal sellNetAmt = sellAmt.subtract(sellAmtFee).subtract(sellTax); // 賣出淨收付

//        未實現損益 = 預估收入 - 買進成本 = 預估賣出淨收付 - 買進淨收付
        BigDecimal unrealProfit = sellNetAmt.subtract(buyNetAmt); // 賣出淨收付 - 買進淨收付
        assertEquals(BigDecimal.valueOf(1347), unrealProfit);
    }

    @Test
    public void testTypeSUnrealProfit() {
//現股交易淨收付計算公式：
//股票市值 = (現價 * 股數) - 賣出手續費 - 交易稅
//未實現損益 = 股票市值（現值） - 買進成本

//        股票市值 = (現價 * 股數) - 賣出手續費 - 交易稅
        BigDecimal curPrice = BigDecimal.valueOf(35);
        BigDecimal qty = BigDecimal.valueOf(1000);
        BigDecimal fee = BigDecimal.valueOf(50);
        BigDecimal tax = BigDecimal.valueOf(105);
        BigDecimal marketValue = curPrice.multiply(qty).subtract(fee).subtract(tax);

//        未實現損益 = 股票市值（現值） - 買進成本
        BigDecimal cost = BigDecimal.valueOf(33498);
        BigDecimal unrealProfit = marketValue.subtract(cost);
        assertEquals(BigDecimal.valueOf(1347), unrealProfit);
    }

////        價金 = 交易股數 × 股票單價
//        BigDecimal 交易股數 = BigDecimal.valueOf(1000);
//        BigDecimal 股票單價 = BigDecimal.valueOf(33.45);
//        BigDecimal 價金 = 交易股數.multiply(股票單價);
//
////        手續費 = 價金 × 手續費費率（手續費通用費率：0.1425%）
//        BigDecimal 手續費 = 價金.multiply(BigDecimal.valueOf(0.001425));
//
////        交易稅 = 價金 × 交易稅稅率（交易稅通用稅率：0.3%）
//        BigDecimal 交易稅 = 價金.multiply(BigDecimal.valueOf(0.003));
}
