package com.green.nowon.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.green.nowon.domain.entity.AttendanceEntity;
import com.green.nowon.domain.entity.EmployeeEntity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
/**
 *
 * @author Hyun
 * AttendanceRegViewDTO = 근태 저장 정보를 보여주는 DTO
 *
 *
 */
@Data
public class AttendanceRegViewDTO {
	private long no;

	private String goWorkTime;

	private String leaveWorkTime;

	private LocalDate attDate;

	private String name;



	/*
	 * public AttendanceRegDTO(AttendanceEntity a) { this.no = a.getNo();
	 * this.goWorkTime = a.getGoWorkTime(); this.leaveWorkTime =
	 * a.getLeaveWorkTime(); }
	 */




	/*
	 * public AttendanceRegDTO(AttendanceEntity a) { this.no=a.getNo();
	 * this.goWorkTime = a.getGoWorkTime(); this.leaveWorkTime =
	 * a.getLeaveWorkTime(); this.attDate = a.getAttDate(); }
	 */
	/**
	 *
	 * @param a 근태 테이블
	 * AttendanceRegViewDTO = 근태 저장 정보를 보여주는 DTO 생성자
	 */
	public AttendanceRegViewDTO(AttendanceEntity a) {
		this.no=a.getNo();
		this.goWorkTime = a.getGoWorkTime().format(DateTimeFormatter.ofPattern("MM/dd HH:mm:ss"));
		if(a.getLeaveWorkTime()!=null) {
			this.leaveWorkTime = a.getLeaveWorkTime().format(DateTimeFormatter.ofPattern("MM/dd HH:mm:ss"));
		}
		//this.leaveWorkTime = a.getLeaveWorkTime();
		this.attDate = a.getAttDate();
		this.name = a.getEmployee().getName();
		//System.err.println(">>>>>>>>name:"+name);
	}




}
