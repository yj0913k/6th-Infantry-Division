package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.DepartmentEntity;
import com.green.nowon.domain.entity.EmployeeEntity;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PaytDetailEmployeeListDTO {
	private long no; //pk
    private String email; //이메일
    private String name; //이름
    //군번
    private String Number; //군번

    private String rank; //rank_no

    //소속번호
    private DepartmentEntity department;

    private LocalDate attDate;


    private long basePay;
    private long nightPay;
    private long overPay;
    private long annualPay;




	public PaytDetailEmployeeListDTO(EmployeeEntity e) {
        System.err.println(e.getRank().getPosition());
        this.no = e.getNo();
        this.Number = e.getNumber();
        this.name = e.getName();
        this.basePay=e.getRank().getBasePay();
        this.nightPay=e.getRank().getNightPay();
        this.overPay=e.getRank().getOverPay();
        this.annualPay=e.getRank().getAnnualPay();
        this.email = e.getEmail();
        this.rank =e.getRank().getPosition();
        this.department=e.getDepartment();

    }





}
