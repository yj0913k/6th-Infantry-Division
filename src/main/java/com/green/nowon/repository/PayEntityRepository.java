package com.green.nowon.repository;

import com.green.nowon.domain.entity.PayEntity;
import com.green.nowon.domain.entity.RankImgEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PayEntityRepository extends JpaRepository<PayEntity, Long> {
    List<PayEntity> findAllByEmployee_no(long no);

	List<PayEntity> findByAccountContaining(String keyword);
}