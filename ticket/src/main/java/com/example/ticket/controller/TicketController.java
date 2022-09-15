package com.example.ticket.controller;

import com.example.ticket.controller.dto.requset.AddRequetBody;
import com.example.ticket.controller.dto.requset.Requstbody;
import com.example.ticket.controller.dto.response.Add;
import com.example.ticket.controller.dto.response.BaseRsDto;
import com.example.ticket.controller.dto.response.Detail;
import com.example.ticket.controller.dto.response.Sum;
import com.example.ticket.model.Entity.Hcmio;
import com.example.ticket.model.Entity.Mstmb;

import com.example.ticket.model.Entity.Tcund;
import com.example.ticket.service.TicketService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/unreal")
@RequiredArgsConstructor
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping
    public List<Hcmio> allTicket() {
        List<Hcmio> hcmios = this.ticketService.getTHcmio();
        return hcmios;
    }

    @GetMapping("/mstmb")
    public List<Mstmb> getMstmb() {
        List<Mstmb> mstmbs = this.ticketService.getMstmb();
        return mstmbs;
    }

    @GetMapping("/tcund")
    public List<Tcund> getTcund() {
        List<Tcund> tcunds = ticketService.getTcund();
        return tcunds;
    }


    /*@PostMapping("/add")
     public  List<> creatTicket(@RequestBody CreateTicket createTicket){

    }*/

    @PostMapping("/detail")
    public BaseRsDto<List<Detail>> getDetiil(@Valid @RequestBody Requstbody requestbody) {
        List<Detail> details = this.ticketService.getDetail(requestbody);
        return new BaseRsDto<>(details, "000", "");
    }

    @PostMapping("/sum")
    public BaseRsDto<List<Sum>> getSum(@Valid @RequestBody Requstbody requstbody) {
        List<Sum> sumList=this.ticketService.getSum(requstbody);
        return new BaseRsDto<>(sumList,"000","");
    }
    @PostMapping("/add")
    public  BaseRsDto<List<Add>> getAdd(@Valid @RequestBody AddRequetBody addRequetBody){
        List<Add> addList=this.ticketService.AddTicket(addRequetBody);
        return new BaseRsDto<>(addList,"000","");
    }

}
