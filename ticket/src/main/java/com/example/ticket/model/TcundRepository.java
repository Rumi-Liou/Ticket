package com.example.ticket.model;

import com.example.ticket.model.Entity.Tcund;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TcundRepository extends JpaRepository<Tcund,String> {
   // @Query("SELECT Qty,RemainQty,Cost From tcund WHERE BranchNo=?1 And CustSeq=?2 And Stock=?3")
   List<Tcund> findByBranchNoAndCustSeq(String branchNo,String custSeq);
   List<Tcund> findByBranchNoAndCustSeqAndStock(String branchNo,String custSeq,String Stock);
}
