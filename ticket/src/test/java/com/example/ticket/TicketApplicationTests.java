package com.example.ticket;

import com.example.ticket.model.Entity.Mstmb;
import com.example.ticket.model.Entity.Tcund;
import com.example.ticket.model.MstmbResponsitory;
import com.example.ticket.model.TcundRepository;
import com.example.ticket.util.TicketUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = TicketApplication.class)
class TicketApplicationTests {

    @Autowired
    TcundRepository tcundRepository;

    @Autowired
    MstmbResponsitory mstmbResponsitory;

    @Test
    public void testTcundRepository() {
        String branchNo = "F62M";
        String custSeq = "4";
        List<Tcund> tcunds = tcundRepository.findByBranchNoAndCustSeq(branchNo, custSeq);
        assertFalse(tcunds.isEmpty());
    }

    @Test
    public void testMarketValue() {
        String branchNo = "F62M";
        String custSeq = "4";
        Tcund tcund = tcundRepository.findByBranchNoAndCustSeq(branchNo, custSeq).get(0);
        Mstmb mstmb = mstmbResponsitory.findByStock(tcund.getStock()).get(0);

        BigDecimal marketValue = TicketUtils.getMarketValue(mstmb.getCurPrice(), tcund.getQty(), TicketUtils.getAmt(mstmb.getCurPrice(), tcund.getQty()));
        assertEquals(BigDecimal.valueOf(1347), marketValue);
    }
}
