package com.green.nowon.domain.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.green.nowon.domain.entity.AttendanceEntity;
import com.green.nowon.domain.entity.EmployeeEntity;

import lombok.Data;

@Data
public class ShowAttendanceListDTO {
private long no;
	
	private String goWorkTime;
	
	private String leaveWorkTime;
	
	private LocalDate attDate;
	
	private EmployeeEntity employee;
	
	private String watchDuty;
	
	private String overTime; 

	private String status; //출근상태
	
	private String annualDay; //계급의 연가.
	//
	
	public ShowAttendanceListDTO(AttendanceEntity A) {
		this.no = A.getNo();
		this.goWorkTime=A.getGoWorkTime().format(DateTimeFormatter.ofPattern("MM/dd HH:mm:ss"));
		this.leaveWorkTime = "--"; 
		this.overTime ="--";
		this.status= "--";
		this.watchDuty="비번";
		this.annualDay="--";
		
		
		if(A.getGoWorkTime()!=null && A.getLeaveWorkTime()==null) {
			this.status="근무 중";
		}
		
		if(A.getLeaveWorkTime()!=null) {
		this.leaveWorkTime = A.getLeaveWorkTime().format(DateTimeFormatter.ofPattern("MM/dd HH:mm:ss"));
		this.status="퇴근";
		
		
		//출근 17일 퇴근 17일, 
		
		//출근 17일 퇴근 18일 01:00 
		
		//정시퇴근 18시(저녁6시)
		
			if(A.getLeaveWorkTime().getDayOfMonth()==(A.getGoWorkTime().getDayOfMonth()+1)) {
				this.overTime = A.getLeaveWorkTime().plusHours(6).format(DateTimeFormatter.ofPattern("HH:mm:ss"));
			}
			
			else if(A.getLeaveWorkTime().getHour()>=18) {
				this.overTime = A.getLeaveWorkTime().minusHours(18).format(DateTimeFormatter.ofPattern("HH:mm:ss"));

			}
			else
			{
				this.overTime = "00 : 00 : 00";
			}
		}
		
		
		System.out.println();
		
		this.attDate = A.getAttDate();
		this.employee=A.getEmployee();
		
		if(A.getEmployee().getRank() !=null) {
			this.annualDay=String.valueOf(A.getEmployee().getRank().getAnnualDay());
		}
		
		if(A.isWatchDuty()==true) {
			this.watchDuty="당직";
		}
		else
		{
			this.watchDuty="비번";
		}
	
		
		
		
	}
}
