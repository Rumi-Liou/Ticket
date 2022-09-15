package com.example.ticket.controller.dto.requset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Requstbody {
    @NotBlank(message = "分行不得為空")
    private String branchNo;
    @NotBlank(message = "客戶帳號不得為空")
    private String custSeq;
    private String stock;
}
