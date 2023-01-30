package com.green.nowon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.nowon.domain.entity.DepartmentEmployeeEntity;

@Repository
public interface DepartmentEmployeeEntityRepository extends JpaRepository<DepartmentEmployeeEntity, Long>{

	List<DepartmentEmployeeEntity> findAllByDepartmentNo(long cateNo);
}
