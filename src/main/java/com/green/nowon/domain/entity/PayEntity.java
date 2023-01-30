package com.green.nowon.domain.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class PayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

    @Column(nullable = false)
    private String account;
    @Column(nullable = false)
    private long totalBasePay; //기본금 토탈

    @Column(nullable = false)
    private long  totalNightPay; //당직 수다당 통합

    @Column(nullable = false)
    private long totalOverPay; //초과 수당 통합급여

    @Column(nullable = false)
    private long totalAnnualPay; //연차 수당 통합급여

    @Column(nullable = false)
    private long totalTotalPay; //연차 수당 통합급여

    @JoinColumn
    @ManyToOne
    private EmployeeEntity employee;


    public PayEntity employee(EmployeeEntity emoployee){
        this.employee=emoployee;
        return this;
    }
}

