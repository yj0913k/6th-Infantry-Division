package com.green.nowon.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.dto.CalendarDeleteDTO;
import com.green.nowon.domain.dto.CalendarInsertDTO;
import com.green.nowon.domain.entity.AttendanceEntity;
import com.green.nowon.domain.entity.CalendarEntity;
import com.green.nowon.repository.AttendanceRepository;
import com.green.nowon.repository.CalendarRepository;
import com.green.nowon.repository.EmployeeEntityRepository;
import com.green.nowon.service.CalendarService;

@Service
public class CalendarServiceProcess implements CalendarService {

	@Autowired
	CalendarRepository calendarRepository;
	
	@Autowired
	AttendanceRepository attendanceRepository;
	
	@Autowired
	EmployeeEntityRepository employeeRepository;
	
	@Override
	public Long register(CalendarInsertDTO calendarInsertDTO) { //full calendar API 에서 가져온 데이터를 DB에 등록하기 위한 함수
		// 그리고 당직근무 날짜가 달력에 지정될 경우, 해당인원의 출근기록의 당직근무 상태가 "비번"에서 "당직"으로 변경됨
		
		CalendarEntity calendarEntity = calendarRepository.save(calendarInsertDTO.toEntity());	
		
		
		List<AttendanceEntity> list = attendanceRepository.findAllByEmployee_No(calendarInsertDTO.getEmployeeNo());
		if(!list.isEmpty()) {
			for(AttendanceEntity i:list) {
				if(i.getGoWorkTime().isAfter(calendarEntity.getStartDate()) 
						&& i.getGoWorkTime().isBefore(calendarEntity.getEndDate()))
						{
							i.changeWatchDuty(true);
							attendanceRepository.save(i);
						}
			}
		}
		
		
		return calendarEntity.getNo();
	}

	@Override
	public List<CalendarEntity> findAll() {
		List<CalendarEntity> list = calendarRepository.findAll();
		return list;
	}

	@Override
	public void delete(CalendarDeleteDTO calendarDeleteDTO) {

		System.err.println(calendarDeleteDTO.getTitle());
		System.err.println(calendarDeleteDTO.getEmployeeName());
		System.err.println(calendarDeleteDTO.getStart().plusHours(9));
		System.err.println(calendarDeleteDTO.getEnd().plusHours(9));
		System.err.println(calendarDeleteDTO.getWorkStatus());
		
		Long employeeNo = employeeRepository.findByNameContaining(calendarDeleteDTO.getEmployeeName()).get(0).getNo();
		
		
		List<CalendarEntity> CalendarList = calendarRepository.findAllByEmployee_No(employeeNo);
		for(CalendarEntity c : CalendarList) {
			System.err.println(c.getNo());
			System.err.println(c.getStartDate());
			System.err.println(c.getEndDate());
			System.err.println(c.getWorkStatus());
			
			
			if(c.getStartDate().equals(calendarDeleteDTO.getStart().plusHours(9)) //JSON으로 가져온 데이터와 DB에 저장된 데이터의 시간차이를 보정해주기 위해 9시간 더해줌
				&& c.getEndDate().equals(calendarDeleteDTO.getEnd().plusHours(9))
				&& c.getWorkStatus().equals( calendarDeleteDTO.getWorkStatus()))
			{
				System.err.println("이거 실행되긴하나?");
				calendarRepository.delete(c);
			
				
				List<AttendanceEntity> list = attendanceRepository.findAllByEmployee_No(employeeNo);
				if(!list.isEmpty()) {
					for(AttendanceEntity a:list) {
						if(a.getGoWorkTime().isAfter(c.getStartDate()) 
								&& a.getGoWorkTime().isBefore(c.getEndDate()))
								{
									a.changeWatchDuty(false);
									attendanceRepository.save(a);
								}
					}
				}
				
				break;
			}
			
			
			
		}
	}
}
