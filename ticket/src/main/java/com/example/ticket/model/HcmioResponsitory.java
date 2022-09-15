package com.example.ticket.model;

import com.example.ticket.model.Entity.Hcmio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface HcmioResponsitory extends JpaRepository<Hcmio,String> {

  // @Query(value = "SELECT TradeDate,Docseq ,Stock From hcmio WHERE  BranchNo=?1 and CustSeq=?2 and Stock=?3 ", nativeQuery = true)
   // List<Hcmio> findByTradeDateAndDocseq(String branchNo, String custSeq);

}
