package com.green.nowon.domain.dto;

import org.springframework.beans.factory.annotation.Autowired;

import com.green.nowon.domain.entity.AddressEntity;
import com.green.nowon.domain.entity.DepartmentEmployeeEntity;
import com.green.nowon.domain.entity.DepartmentEntity;
import com.green.nowon.domain.entity.EmployeeEntity;

import lombok.Data;


@Data
public class YWEmployeeListDTO {

	private long no;
	private String email;
	private String name;
	private String joinDate;
	private String rankPosition;
	private DepartmentEntity department;
	private String phone;
	private AddressEntity address;
	 private String number; //군번
	
	//이미지 대표이미지

	public YWEmployeeListDTO(EmployeeEntity e) {
		this.no = e.getNo();
		this.email = e.getEmail();
		this.name = e.getName();
		this.joinDate = e.getJoinDate();
		this.rankPosition = e.getRank().getPosition();
		this.department=e.getDepartment();//최상위소속
		this.phone=e.getPhone();
		this.address=e.getDepartment().getAddress();
		this.number=e.getNumber();
	}
	
	public YWEmployeeListDTO(DepartmentEmployeeEntity DED) {

		this(DED.getEmployee());
		//DED.getEmployee().
	}
	
}
