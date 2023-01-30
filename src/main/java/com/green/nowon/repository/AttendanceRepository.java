package com.green.nowon.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.nowon.domain.entity.AttendanceEntity;
import com.green.nowon.domain.entity.EmployeeEntity;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {

	Optional<AttendanceEntity> findByEmployee_No(long no);

	Optional<AttendanceEntity> findByNo(long no);

	List<AttendanceEntity> findAllByEmployee_No(long no);

	List<AttendanceEntity> findById(long mno);

	Optional<AttendanceEntity> findByGoWorkTime(int goWorkTime);

	Optional<AttendanceEntity> findByLeaveWorkTimeIsNullAndEmployeeNo(long no);

	// @Query("select a from AttendanceEntity a where a.leaveWorkTime= null and
	// a.employee.no = :no")
	// Optional<AttendanceEntity> find(@Param("no") long no);

	Optional<AttendanceEntity> findAllByNo(long no);

	List<AttendanceEntity> findByEmployee(EmployeeEntity en);

}
