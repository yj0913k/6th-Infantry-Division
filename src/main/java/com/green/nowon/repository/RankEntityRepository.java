package com.green.nowon.repository;

import com.green.nowon.domain.entity.AttendanceEntity;
import com.green.nowon.domain.entity.EmployeeEntity;
import com.green.nowon.domain.entity.RankEntity;
import com.green.nowon.domain.entity.RankImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface RankEntityRepository extends JpaRepository<RankEntity, Long> {


    List<RankEntity> findAllByNo(String no);


    Optional<RankEntity> save(RankImgEntity rankListImg);

    Optional<RankEntity> findByPosition(String position);


}
