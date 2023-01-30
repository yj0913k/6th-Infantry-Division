package com.green.nowon.service;

import java.util.List;

import com.green.nowon.domain.dto.CalendarDeleteDTO;
import com.green.nowon.domain.dto.CalendarInsertDTO;
import com.green.nowon.domain.entity.CalendarEntity;

public interface CalendarService {
	
	Long register(CalendarInsertDTO calendarInsertDTO);

	List<CalendarEntity> findAll();

	void delete(CalendarDeleteDTO calendarDeleteDTO);
}
