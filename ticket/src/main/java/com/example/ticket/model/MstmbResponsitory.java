package com.example.ticket.model;

import com.example.ticket.model.Entity.Mstmb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MstmbResponsitory extends JpaRepository<Mstmb,String> {
 //   @Query("SELECT StockName,BuyPrice,nowPrice From mstmb WHERE Stock=?1")
    List<Mstmb> findByStock(String stock);

}
