package com.green.nowon.repository;

import com.green.nowon.domain.entity.RankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankImgRepository extends JpaRepository<RankEntity, Long> {
}