package com.green.nowon.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.domain.dto.CalendarDeleteDTO;
import com.green.nowon.domain.dto.CalendarInsertDTO;
import com.green.nowon.domain.entity.CalendarEntity;
import com.green.nowon.security.MyUserDetails;
import com.green.nowon.service.AttendanceService;
import com.green.nowon.service.CalendarService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class AttendanceController {

	@Autowired
	AttendanceService service;

	@Autowired
	CalendarService calendarService;

//	깃 브렌치용!
	@GetMapping("/attendance") // 직원들의 출,퇴근 상황을 보여주는 리스트 페이지로 이동시켜줌
	// 대대별로 선택해서 내용을 뿌려줄 수 있도록 구성하려고 함(80명 80명씩, DB의 당일날 정보만)
	public String attendance(Model model) {
		service.showAttendEmployee(model);
		return "attendance/attendance";
	}

	@GetMapping("/attendance/group/{selectValue}")
	// 출퇴근기록 조회페이지에서 셀렉트 옵션으로 대대를 선택했을 때, 대대별로 정보를 얻기위한 페이지
	public String selectValue(@PathVariable long selectValue, Model model) {
		service.showAttendEmployee(selectValue, model);
		// return "attendance/attendance";
		return "attendance/selectGroup";
	}

	@GetMapping("/attendance/ModalGroup/{selectValue}")
	// 모달창에서 대대를 선택할 때, 바로 옆에서 해당부대에 속해있는 부대원 목록 셀렉트박스를 생성해주는 컨트롤러
	public String ModalGroupSelect(@PathVariable long selectValue, Model model) {
		service.modalSelectGroup(selectValue, model);
		return "attendance/ModalSelectGroup";
	}

	@GetMapping("/my-attendance") // 출근,퇴근 버튼으로 등록 할 수 있는 페이지로 이동시켜줌(추후 메인페이지에 삽입 될 것)
	public String myAttendance(@AuthenticationPrincipal MyUserDetails userDetails, Model model) {
		long mno = userDetails.getMno();
		service.showInOut(mno, model);

		service.myAttendanceList(mno, model); // 개인 출근기록 테이블 만들기 위한 코드(현석)
		return "attendance/my-attendance";
	}

	@PostMapping("/attendance/my-attendance") // 출근 버튼 눌렀을 때, DB에 출근 시간이 찍히도록 처리해주는 매핑
	public String regWorkTime(long mno, Model model) {

		service.regWorkTime(mno, model);// DB에 출근시간 저장
		service.showInOut(mno, model);
		service.myAttendanceList(mno, model); // 개인 출근기록 테이블 만들기 위한 코드(현석)
		return "attendance/my-attendance";
	}

	@PostMapping("/attendance/leave") // 퇴근 버튼 눌렀을때, 현재 로그인된 유저의 퇴근기록중 null을 찾아서 현재 시간을 저장해주는 매핑
	public String leaveTime(long mno, Model model) {

		service.regLeaveTime(mno); // DB에 퇴근시간 저장
		service.showInOut(mno, model); //
		service.myAttendanceList(mno, model); // 개인 출근기록 테이블 만들기 위한 코드(현석)
		return "attendance/my-attendance";
	}

	@PostMapping("/attendance/registerCalender")
	public ResponseEntity<Long> register(@RequestBody CalendarInsertDTO calendarInsertDTO) {
		System.err.println(">>>>>>>>>>>>>>>>>>" + calendarInsertDTO);
		log.info(calendarInsertDTO);
		Long calendarNo = calendarService.register(calendarInsertDTO);
		System.err.println("컨트롤러단 캘린더 저장성공");
		return new ResponseEntity<>(calendarNo, HttpStatus.OK);
	}

	@GetMapping("/full-calendar/calendar-admin") //DB에서 캘린더페이지로 데이터를 전달하여 화면에 표시
	@ResponseBody
	public List<Map<String, Object>> monthPlan() {
		
		System.err.println("DB에서 캘린더 이벤트 가져오기! 컨트롤러 진입성공");
		List<Map<String,Object>> paramList = new ArrayList<Map<String,Object>>();
		
		List<CalendarEntity> listAll = calendarService.findAll();
		System.err.println("listAll 확인해보자"+listAll
				);
		for (int i = 0; i < listAll.size(); i++) {
			Map<String, Object> param = new HashMap<String,Object>();
			param.put("title", listAll.get(i).getEmployee().getName());
			param.put("start", listAll.get(i).getStartDate());
			param.put("end", listAll.get(i).getEndDate());
			param.put("workStatus", listAll.get(i).getWorkStatus());
			paramList.add(param);
			System.err.println("paramList for문 내부" + paramList);
			
		}
		return paramList;
	}


	@DeleteMapping("/full-calendar/calendar-admin-delete")// 풀캘린더에서 일정을 삭제할때, DB에서도 삭제해주고, AttendanceEntity의 watchDuty의 내용도 "당직"에서 "비번"으로 바꿔줌
	@ResponseBody
	public void deleteCalendar(@RequestBody CalendarDeleteDTO calendarDeleteDTO) {
		System.err.println("DB의 캘린더 이벤트 삭제하기! 컨트롤러 진입성공");
		calendarService.delete(calendarDeleteDTO);

	}
//	@GetMapping("/")//출근,퇴근 버튼으로 등록 할 수 있는 페이지로 이동시켜줌(추후 메인페이지에 삽입 될 것)
//	public String Attendance(@AuthenticationPrincipal MyUserDetails userDetails ,Model model) {
//		long mno = userDetails.getMno();
//		service.showInOut(mno, model);
//
//		//public String myAttendance(Principal principal, Model model) {
//		//	long mno = service.myAttendanceMno(principal);	//개인 출근기록 테이블 만들기 위한 코드(현석)
//		service.myAttendanceList(mno, model);	//개인 출근기록 테이블 만들기 위한 코드(현석)
//		return "index";
//	}

}
