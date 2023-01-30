package com.green.nowon.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.green.nowon.domain.entity.AttendanceEntity;
import com.green.nowon.domain.entity.EmployeeEntity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AttendanceRegDTO {
private long no;
   
   private LocalDateTime goWorkTime;
   
   private LocalDateTime leaveWorkTime;
   
   private LocalDate attDate;
   
   private long employee;
   

   
   public AttendanceEntity totEntity() {
      return AttendanceEntity.builder()
            .no(no)
            .goWorkTime(goWorkTime)
            .leaveWorkTime(leaveWorkTime)
            .attDate(attDate)
            .build();
      
   }
   
}
	
	

	
	
	
