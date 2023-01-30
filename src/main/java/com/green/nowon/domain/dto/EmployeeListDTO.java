package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.DepartmentEntity;
import com.green.nowon.domain.entity.EmployeeEntity;

import com.green.nowon.repository.EmployeeEntityRepository;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Data
public class EmployeeListDTO {
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
    private DepartmentEntity department;
    
    private String defImg;



	public EmployeeListDTO(EmployeeEntity e) {
        System.err.println(e.getRank().getPosition());
        this.no = e.getNo();
        this.pass = e.getPass();
        this.number = e.getNumber();
        this.name = e.getName();
        this.email = e.getEmail();
        this.rank =e.getRank().getPosition();
        this.joinDate = e.getJoinDate();
        this.deleted = e.isDeleted();
        this.phone = e.getPhone();
        this.defImg=e.defImg().getUrl()+e.defImg().getNewName();
        this.birth =e.getBirth(); // 머지 주의사항
        this.department=e.getDepartment();
    }





}
