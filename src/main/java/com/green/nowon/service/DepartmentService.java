package com.green.nowon.service;

import org.springframework.ui.Model;

import com.green.nowon.domain.dto.AddressInfoDTO;

public interface DepartmentService {

	void save(String[] departmentName, AddressInfoDTO dto);

	boolean isReg(String text);

	void fistDepartment(Model model);

	void departmentList(Long parentNo, Model model);

	void employeesOfDepartment(long cateNo, Model model);
}
