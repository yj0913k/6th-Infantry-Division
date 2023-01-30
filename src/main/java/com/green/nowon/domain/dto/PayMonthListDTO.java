package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.EmployeeEntity;
import lombok.Data;

@Data
public class PayMonthListDTO {
	private long no;
    private String email; //이메일
    private String name; //이름
    private String pass; //비밀번호
    private String joinDate; //입대일

    private String birth;

    private boolean deleted; //삭제여부 1:가입중 0: 탈퇴
    private String phone;

    //군번
    private String number; //군번
    //주소

    //계급번호

    private String rank; //rank_no

    //소속번호
    private long department;



    private long basePay;
    private long nightPay;
    private long overPay;
    private long annualPay;
    private int annualDay;
    private long totAnnualPay;
	public PayMonthListDTO(EmployeeEntity e) {

        this.no = e.getNo();
        this.pass = e.getPass();
        this.number = e.getNumber();
        this.name = e.getName();
        this.email = e.getEmail();
        this.rank =e.getRank().getPosition();
        this.department=e.getDepartment().getNo();
        this.joinDate = e.getJoinDate();
        this.deleted = e.isDeleted();
        this.phone = e.getPhone();
        this.birth =e.getBirth();
        this.basePay=e.getRank().getBasePay();
        this.nightPay=e.getRank().getNightPay();
        this.overPay=e.getRank().getOverPay();
        this.annualDay=e.getRank().getAnnualDay();
        this.annualPay=e.getRank().getAnnualPay();
    }

    
    
    
}
