package com.green.nowon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.nowon.domain.entity.DepartmentEntity;

@Repository
public interface DepartmentEntityRepository extends JpaRepository<DepartmentEntity, Long>{

	//Optional<DepartmentEntity> findByName(String name);
	//1차카테고리 조회
	Optional<DepartmentEntity> findByParentNoNullAndName(String name);
	//sub카테고리 조회
	Optional<DepartmentEntity> findByParentNoAndName(Long parentNo, String name);
	
	//List<DepartmentEntity> findByDepthOrderByNameAsc(int depth);
	
	//List<DepartmentEntity> findByParentNoIsNullOrderByNameAsc();
	
	
	//지금 6사단 프로젝트에서 지금이것만 사용중인데, 추후에 위의 다른 함수들도 사용될 수 있음. 아직 판단 보류중
	List<DepartmentEntity> findByParentNoOrderByNameAsc(Long parentNo);
	List<DepartmentEntity> findByDepth(int i);
	

//	List<EmployeeEntity> findAllByNo(long departmentNo);
}
