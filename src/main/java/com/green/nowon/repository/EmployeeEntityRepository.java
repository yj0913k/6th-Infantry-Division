package com.green.nowon.repository;

import com.green.nowon.domain.dto.EmployeeInsertDTO;
import com.green.nowon.domain.dto.EmployeeListDTO;
import com.green.nowon.domain.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, Long> {
    Optional<EmployeeEntity> findByEmailAndDeleted(String username, boolean b);

    EmployeeEntity findAllByEmail(String email);



    List<EmployeeEntity> findByNameContaining(String searchKeyword);

    Page<EmployeeEntity> findByNameContaining(String searchKeyword, Pageable pageable);







    Optional<EmployeeEntity> findByNumber(String no);
}
