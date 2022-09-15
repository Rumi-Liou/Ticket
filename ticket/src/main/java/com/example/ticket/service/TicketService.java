package com.example.ticket.service;

import com.example.ticket.Exception.TicketException;
import com.example.ticket.controller.dto.requset.AddRequetBody;
import com.example.ticket.controller.dto.requset.Requstbody;
import com.example.ticket.controller.dto.response.Add;
import com.example.ticket.controller.dto.response.Detail;
import com.example.ticket.controller.dto.response.Sum;
import com.example.ticket.model.Entity.Hcmio;
import com.example.ticket.model.Entity.Mstmb;
import com.example.ticket.model.Entity.Tcund;
import com.example.ticket.model.HcmioResponsitory;
import com.example.ticket.model.MstmbResponsitory;
import com.example.ticket.model.TcundRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.example.ticket.util.TicketUtils.*;
import static java.math.RoundingMode.HALF_UP;
import static java.util.Objects.requireNonNullElse;

@Service
@Slf4j
@RequiredArgsConstructor
public class TicketService {

    private final HcmioResponsitory hcmioResponsitory;
    private final MstmbResponsitory mstmbResponsitory;
    private final TcundRepository tcundRepository;

    public List<Hcmio> getTHcmio() {
        return hcmioResponsitory.findAll();
    }

    public List<Mstmb> getMstmb() {
        return mstmbResponsitory.findAll();
    }

    public List<Tcund> getTcund() {
        return tcundRepository.findAll();
    }

    public List<Detail> getDetail(Requstbody requstbody) {
        List<Detail> detailList = new ArrayList<>();
        String branchNo = requstbody.getBranchNo();
        String stock = requireNonNullElse(requstbody.getStock(), "");
        String custSeq = requstbody.getCustSeq();
        List<Tcund> tcundList;
        if (stock.isBlank()) {
            tcundList = tcundRepository.findByBranchNoAndCustSeq(branchNo, custSeq);
        } else {
            tcundList = tcundRepository.findByBranchNoAndCustSeqAndStock(branchNo, custSeq, stock);
        }
        List<Mstmb> mstmbList;
        for (Tcund tcund : tcundList) {
            Detail detail = new Detail();
            detail.setTradeDate(tcund.getTradeDate());
            detail.setDocSeq(tcund.getDocSeq());
            detail.setStock(tcund.getStock());
            detail.setBuyPrice(tcund.getPrice().setScale(2, HALF_UP));
            detail.setFee(tcund.getFee().setScale(0, HALF_UP));
            detail.setQty(tcund.getQty().setScale(0, HALF_UP));
            detail.setCost(tcund.getCost().setScale(0, HALF_UP));
            detail.setRemainQty(tcund.getRemainQty().setScale(0, HALF_UP));
            if (stock.isBlank()) {
                mstmbList = mstmbResponsitory.findByStock(tcund.getStock());
            } else {
                mstmbList = mstmbResponsitory.findByStock(stock);
            }
            for (Mstmb mstmb : mstmbList) {
                detail.setStockName(mstmb.getStockName());
                detail.setNowPrice(mstmb.getCurPrice().setScale(2, HALF_UP));
                BigDecimal amt = getAmt(mstmb.getCurPrice(), tcund.getQty());
                BigDecimal fee = getFee(amt);
                BigDecimal tax = getTax(amt);
                BigDecimal marketValue = getMarketValue(amt, fee, tax);
                detail.setMarketValue(marketValue);
                BigDecimal unrealProfit = getUnrealProfit(marketValue, tcund.getCost());
                detail.setUnrealProfit(unrealProfit);
            }
            detailList.add(detail);
        }
        if (detailList.isEmpty()) {
            throw new TicketException("001", "查無符合資料");
        }
        return detailList;
    }

    public List<Sum> getSum(Requstbody requstbody) {
        List<Sum> sumList = new ArrayList<>();
        List<Tcund> tcundList;
        List<Mstmb> mstmbList;
        List<Detail> details = new ArrayList<>();
        String branchNo = requstbody.getBranchNo();
        String custSeq = requstbody.getCustSeq();
        String stock = requstbody.getStock();
        if (stock.isBlank()) {
            tcundList = tcundRepository.findByBranchNoAndCustSeq(branchNo, custSeq);
        } else {
            tcundList = tcundRepository.findByBranchNoAndCustSeqAndStock(branchNo, custSeq, stock);
        }
        for (Tcund tcund : tcundList) {
            Sum sum = new Sum();
            Detail detail = new Detail();
            if (stock.isBlank()) {
                mstmbList = mstmbResponsitory.findByStock(tcund.getStock());
            } else {
                mstmbList = mstmbResponsitory.findByStock(stock);
            }
            for (Mstmb mstmb : mstmbList) {
                sum.setStock(requstbody.getStock());
                sum.setStockName(mstmb.getStockName());
                sum.setNowPrice(mstmb.getCurPrice());
                sum.setSumRemainQty(sum.getSumRemainQty().add(tcund.getRemainQty()));
                sum.setSumFee(tcund.getFee().add(tcund.getFee()));
                sum.setSumCost(tcund.getCost().add(tcund.getCost()));
                BigDecimal amt = getAmt(mstmb.getCurPrice(), tcund.getQty());
                BigDecimal fee = getFee(amt);
                BigDecimal tax = getTax(amt);
                BigDecimal marketValue = getMarketValue(amt, fee, tax);
                sum.setSumMarketValue(marketValue.add(marketValue));
                BigDecimal unrealProfit = getUnrealProfit(marketValue, tcund.getCost());
                sum.setSumUnrealProfit(sum.getSumCost().add(unrealProfit));
                detail.setTradeDate(tcund.getTradeDate());
                detail.setDocSeq(tcund.getDocSeq());
                detail.setStock(tcund.getStock());
                detail.setBuyPrice(tcund.getPrice().setScale(2, HALF_UP));
                detail.setFee(tcund.getFee().setScale(0, HALF_UP));
                detail.setQty(tcund.getQty().setScale(0, HALF_UP));
                detail.setCost(tcund.getCost().setScale(0, HALF_UP));
                detail.setRemainQty(tcund.getRemainQty().setScale(0, HALF_UP));
                detail.setStockName(mstmb.getStockName());
                detail.setNowPrice(mstmb.getCurPrice().setScale(2, HALF_UP));
                detail.setMarketValue(marketValue);
                detail.setUnrealProfit(unrealProfit);
            }
            details.add(detail);
            sum.setDetaiList(details);
            sumList.add(sum);
        }
        return sumList;
    }

    public List<Add> AddTicket(AddRequetBody addRequetBody) {
        Add add = new Add();
        Detail detail=new Detail();
        String tradeDate = addRequetBody.getTradeDate();
        String branchNo = addRequetBody.getBranchNo();
        String custseq = addRequetBody.getCustSeq();
        String docSeq = addRequetBody.getDocSeq();
        List<Add> addList = new ArrayList<>();
        List<Mstmb> mstmbList=mstmbResponsitory.findByStock(addRequetBody.getStock());
        List<Tcund> tcundList=tcundRepository.findByBranchNoAndCustSeqAndStock(branchNo,custseq,addRequetBody.getStock());
//        if (branchNo.isEmpty() && branchNo.isEmpty() && custseq.isEmpty() && docSeq.isEmpty()) {
//            throw new TicketException("002", "該筆資料已經存在");
//        }
        add.setTradeDate(tradeDate);
        add.setDocSeq(docSeq);
        add.setStock(addRequetBody.getStock());
        add.setBuyPrice(addRequetBody.getPrice());
        for (Tcund tcund:tcundList){
        for(Mstmb mstmb:mstmbList){
        add.setStockName(mstmb.getStockName());
        add.setNowPrice(mstmb.getCurPrice());
        add.setQty(addRequetBody.getQty());
        add.setRemainQty(tcund.getRemainQty());
        BigDecimal amt=getAmt(addRequetBody.getPrice(),addRequetBody.getQty());
        BigDecimal fee=getFee(amt);
        add.setFee(fee);
        add.setCost(tcund.getCost());
        BigDecimal tax = getTax(amt);
        BigDecimal marketValue = getMarketValue(amt, fee, tax);
        add.setMarketValue(marketValue);
        BigDecimal unrealProfit = getUnrealProfit(marketValue, tcund.getCost());
        add.setUnrealProfit(unrealProfit);
        }
        addList.add(add);
        }
        return  addList;
    }
}

