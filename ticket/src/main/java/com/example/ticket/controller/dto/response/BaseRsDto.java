package com.example.ticket.controller.dto.response;

import com.example.ticket.controller.dto.requset.Requstbody;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseRsDto<T> {
    private T resultList;
    private String status;
    private String message;

    public BaseRsDto(T resultList) {
       this.resultList=resultList;
    }

    public BaseRsDto(String status, String message) {
        this.status=status;
        this.message=message;
    }
}