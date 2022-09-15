package com.example.ticket.Advice;

import com.example.ticket.Exception.TicketException;
import com.example.ticket.controller.dto.response.BaseRsDto;
import com.example.ticket.controller.dto.response.Detail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class TicketAdvice {
    @ExceptionHandler(TicketException.class)
    public ResponseEntity<BaseRsDto<?>> ticketException(TicketException ticketException){
      //  BaseRsDto<List<Detail>> BaseRsDto = null;

        return ResponseEntity.ok(new BaseRsDto<>(ticketException.getStatus(),ticketException.getMessage()));
    }
    @ExceptionHandler(BindException.class)
    public  ResponseEntity<BaseRsDto<?>>  bindException(BindException e){
        BaseRsDto<?> baseRsDto=new BaseRsDto<>();
        baseRsDto.setStatus("002");
        baseRsDto.setMessage(e.getAllErrors().get(0).getDefaultMessage());
        return ResponseEntity.ok(baseRsDto);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseRsDto<?>> exception(Exception e){
        return ResponseEntity.ok(new BaseRsDto<>("005","伺服器忙碌中，請稍後嘗試"));
    }
}
