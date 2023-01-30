package com.green.nowon.domain.dto;

import java.time.LocalDateTime;

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
public class CalendarDeleteDTO {

//	private Long no; 
	
	//private EmployeeEntity employee;
	private String title;
	//클라이언트 JSON으로 받아오는 데이터에서 title의 내용은 "[당직]김영욱" 이런 형식으로 되어있음. 그래서 문자열중에서 "당직" 과 "김영욱"을 추출해야함. 이는 프로세스 단에서 해두었음 
	
	private LocalDateTime start; 
	//9시간 더 더해주어야함. 풀캘린더 JSON 데이터는 15:00로 값이 되어있고, DB에 저장된것은 24:00로 저장되어 9시간 차이가 생김. 이를 보정해야함
	//보정은 여기가 아닌, 서비스프로세스 단에서 해주었음
	
	private LocalDateTime end; 
	//마찬가지로 9시간 더 더해주어야함. 풀캘린더 JSON 데이터는 15:00로 값이 되어있고, DB에 저장된것은 24:00로 저장되어 9시간 차이가 생김. 이를 보정해야함
	
	
	
	public String getEmployeeName() {
		
		String str = this.title;
		return str.substring(4); // [당직]김영욱 에서 '김' 이 인덱스로 4이다. 그래서 '김영욱'을 가져올수 있다.
	}
	
	public WorkStatus getWorkStatus() {
		WorkStatus status = WorkStatus.비번;
		String str = this.title.substring(1, 3);
		if(str.equals("당직"))
		{
			status=WorkStatus.당직;
		}
		else if(str.equals("휴가"))
		{
			status=WorkStatus.휴가;
		}
		else if(str.equals("병가"))
		{
			status=WorkStatus.병가;
		}
			return status;
	}
	
	
}
			