package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.DepartmentEntity;
import com.green.nowon.domain.entity.EmployeeEntity;

import com.green.nowon.repository.EmployeeEntityRepository;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Data
public class PayEmployeeListDTO {
	private long no;
    private String name; //이름

    private boolean deleted; //삭제여부 1:가입중 0: 탈퇴
    //군번
    private String Number; //군번
    //주소

    //계급번호
    private String rank; //rank_no

    //소속번호
    private DepartmentEntity department;


	public PayEmployeeListDTO(EmployeeEntity e) {
        System.err.println(e.getRank().getPosition());
        this.no = e.getNo();
        this.Number = e.getNumber();
        this.name = e.getName();
        this.rank =e.getRank().getPosition();
        this.deleted = e.isDeleted();
        this.department=e.getDepartment();
    }
}
