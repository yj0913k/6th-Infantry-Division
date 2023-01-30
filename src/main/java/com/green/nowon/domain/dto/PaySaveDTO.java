package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.PayEntity;
import lombok.Data;

@Data
public class PaySaveDTO {

    private long no;
    private String account; //지급일

    private long totalBasePay;
    private long totalNightPay;
    private long totalOverPay;

    private long totalAnnualPay;

    private long totalTotalPay;

    public PayEntity toEntity() {
      return PayEntity.builder()
              .no(no)
              .account(account)
              .totalBasePay(totalBasePay)
              .totalNightPay(totalNightPay)
              .totalOverPay(totalOverPay)
              .totalAnnualPay(totalAnnualPay)
              .totalTotalPay(totalTotalPay)
              .build();
    }
}
