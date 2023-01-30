package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.PayEntity;
import lombok.Data;

@Data
public class PaySlipListDTO {

    private long no;
    private String name;

    private String account; //지급일
    private String position;

    private long totalTotalPay;


    public PaySlipListDTO(PayEntity e) {
        this.no = e.getNo();
        this.name = e.getEmployee().getName();
        this.account = e.getAccount();
        this.position = e.getEmployee().getRank().getPosition();
        this.totalTotalPay = e.getTotalTotalPay();
    }
}
