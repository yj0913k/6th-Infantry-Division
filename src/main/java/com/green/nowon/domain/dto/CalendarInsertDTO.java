package com.green.nowon.domain.dto;

import java.time.LocalDateTime;

import com.green.nowon.domain.entity.CalendarEntity;
import com.green.nowon.domain.entity.EmployeeEntity;
import com.green.nowon.domain.entity.WorkStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CalendarInsertDTO {

//	private Long no; 
	
	//private EmployeeEntity employee;
	private long employeeNo;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
	
	private WorkStatus workStatus;

	
    public CalendarEntity toEntity(){
        return CalendarEntity.builder()
        		.employee(EmployeeEntity.builder().no(employeeNo).build())
                .startDate(startDate.plusHours(9))
                .endDate(endDate.plusHours(9))
                .workStatus(workStatus)
                .build();
  }
}
			