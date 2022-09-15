package com.example.ticket.Exception;

import com.example.ticket.controller.dto.requset.Requstbody;
import com.example.ticket.controller.dto.response.Detail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Field;
import java.net.http.HttpResponse;
import java.util.List;


@NoArgsConstructor
public class TicketException extends RuntimeException{
    String status;
    String message;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public TicketException(String status, String message){
        super(message);
       this.status=status;
       this.message=message;
    }

}
