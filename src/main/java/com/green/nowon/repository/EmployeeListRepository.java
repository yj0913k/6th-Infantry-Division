package com.green.nowon.repository;

import com.green.nowon.domain.entity.EmployeeEntity;
import com.green.nowon.domain.entity.RankEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeListRepository extends JpaRepository<EmployeeEntity, Long> {

    Page<EmployeeEntity> findByNameContaining(String searchName, Pageable pageable);
}

