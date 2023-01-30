package com.green.nowon.domain.dto;


import com.green.nowon.domain.entity.DepartmentEntity;
import com.green.nowon.domain.entity.PayEntity;
import lombok.Data;

@Data
public class PayDetailDTO {


    private long no;
    private String name;

    private DepartmentEntity department;

    private String Number;

    private String email;

    private String account; //지급일
    private String position;

    private long totalTotalPay;

    private long totalBasePay;
    private long totalNightPay;
    private long totalOverPay;

    private long totalAnnualPay;




    public PayDetailDTO(PayEntity e) {
        this.no = e.getNo();
        this.email=e.getEmployee().getEmail();
        this.Number=e.getEmployee().getNumber();
        this.name = e.getEmployee().getName();
        this.department = e.getEmployee().getDepartment();
        this.account = e.getAccount();
        this.position = e.getEmployee().getRank().getPosition();
        this.totalTotalPay = e.getTotalTotalPay();
        this.totalBasePay = e.getTotalBasePay();
        this.totalNightPay= e.getTotalNightPay();
        this.totalOverPay = e.getTotalOverPay();
        this.totalAnnualPay = e.getTotalAnnualPay();
    }




}
