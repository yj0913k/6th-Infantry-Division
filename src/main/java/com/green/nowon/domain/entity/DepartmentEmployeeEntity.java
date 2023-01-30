package com.green.nowon.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class DepartmentEmployeeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	
	@JoinColumn//category_no
	@ManyToOne
	private DepartmentEntity department;
	
	@JoinColumn//item_no
	@ManyToOne
	private EmployeeEntity employee;

}
