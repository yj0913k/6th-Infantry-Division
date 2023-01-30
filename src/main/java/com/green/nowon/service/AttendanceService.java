package com.green.nowon.service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.ui.Model;

import com.green.nowon.domain.dto.AttendanceRegDTO;
import com.green.nowon.domain.entity.AttendanceEntity;

public interface AttendanceService {



	void showInOut(long mno, Model model);

	long myAttendanceMno(Principal principal);

	void myAttendanceList(long mno, Model model);

	
   void regWorkTime(long mno, Model model);
   
   void regLeaveTime(long mno);

   long findPk(long mno);
   void showAttendEmployee(Model model); //조회페이지 전체 대대 다 보여줌

   void showAttendEmployee(long selectValue, Model model); //조회페이지에서 특정 대대 선택시 해당 대대만 보여줌

   void modalSelectGroup(long selectValue,Model model);

	   	
}


