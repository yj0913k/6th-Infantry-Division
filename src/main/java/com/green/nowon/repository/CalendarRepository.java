package com.green.nowon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.nowon.domain.entity.CalendarEntity;

@Repository
public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {

	List<CalendarEntity> findAllByEmployee_No(long no);
}
