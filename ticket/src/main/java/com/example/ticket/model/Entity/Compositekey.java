package com.example.ticket.model.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public  class Compositekey implements Serializable {
    private String tradeDate;
    private String branchNo;
    private String custSeq;
    private String docSeq;
}
