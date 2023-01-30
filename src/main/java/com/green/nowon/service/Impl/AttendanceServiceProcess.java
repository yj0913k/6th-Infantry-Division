package com.green.nowon.service.Impl;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.AttendanceRegViewDTO;
import com.green.nowon.domain.dto.EmployeeListDTO;
import com.green.nowon.domain.dto.ShowAttendanceListDTO;
import com.green.nowon.domain.entity.AttendanceEntity;
import com.green.nowon.domain.entity.EmployeeEntity;
import com.green.nowon.repository.AttendanceRepository;
import com.green.nowon.repository.DepartmentEntityRepository;
import com.green.nowon.repository.EmployeeEntityRepository;
import com.green.nowon.service.AttendanceService;

@Service
public class AttendanceServiceProcess implements AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Autowired
	private EmployeeEntityRepository employeeEntityRepository;
	// override attendanceRepository.findby

	@Autowired
	private DepartmentEntityRepository departmentEntityRepo;

	@Override
	public void showAttendEmployee(Model model) {

		// 추후에 선택한 날짜와 분대별로 출근기록 볼 수 있도록 바꾸기
		model.addAttribute("Attendancelist",
				attendanceRepository.findAll().stream().map(ShowAttendanceListDTO::new)
				.collect(Collectors.toList()));
		model.addAttribute("departmentList", departmentEntityRepo.findByDepth(1));
	}

	//////////////////////////////////////// 출근버튼 기능구현(영욱님과
	//////////////////////////////////////// 겹쳐요)//////////////////////////////

	// 출퇴근기록 조회페이지에서 셀렉트 옵션으로 대대를 선택했을 때, 대대별로 정보를 얻기위한 페이지
	@Override
	public void showAttendEmployee(long selectValue, Model model) {

		List<ShowAttendanceListDTO> resultList = new ArrayList<>();

		List<ShowAttendanceListDTO> list = attendanceRepository.findAll().stream().map(ShowAttendanceListDTO::new)
				.collect(Collectors.toList());

		if (!list.isEmpty()) { // DB에서 가져온 정보가 존재할때 아래 코드를 실행
			for (ShowAttendanceListDTO dto : list) {
				if (dto.getEmployee().getDepartment().getParent().getParent().getParent().getNo() == selectValue) {
					// 선택한 대대와 일치하는 부대원을 resultList에 새로 저장해주고, resultList를 결과화면에 뿌릴 생각
					resultList.add(dto);
				}
			}
		}

		model.addAttribute("Attendancelist", resultList);
		model.addAttribute("departmentList", departmentEntityRepo.findByDepth(1));
	}

////////////////////////////////////////개인출근기록 테이블로 뿌려주기//////////////////////////////	
	@Override // 로그인한 유저의 사원번호를 가져오기 위한 코드
	public long myAttendanceMno(Principal principal) {
		String mno = principal.getName();
		EmployeeEntity employeeEntity = employeeEntityRepository.findAllByEmail(mno);// email 로 부터 찾는다
		System.out.println("mno : " + employeeEntity.getNo());
		return employeeEntity.getNo();
	}

	@Override // 로그인한 유저의 사원번호를 가져와서 출근기록 뿌려줘요(OrderBy 적용해서 더 다듬을 예정)
	public void myAttendanceList(long mno, Model model) {
		List<AttendanceRegViewDTO> list = attendanceRepository.findAllByEmployee_No(mno).stream()
				.map(AttendanceRegViewDTO::new)// map = 모아서 저장한다
				.collect(Collectors.toList());// collect = 생성자를 맵 형태로 모은다

		model.addAttribute("list", list);

	}
////////////////////////////////////////개인출근기록 테이블로 뿌려주기 끝//////////////////////////////	

	@Override // 출근버튼을 눌렀을때, DB에 출근기록이 생성되는 함수
	public void regWorkTime(long mno, Model model) {

		List<AttendanceEntity> list = attendanceRepository.findAllByEmployee_No(mno);

		if (list.isEmpty()) {// DB에 레코드가 하나도 없을떄, 레코드를 생성해줌
			attendanceRepository.save(
					AttendanceEntity.builder().employee(employeeEntityRepository.findById(mno).orElseThrow()).build());
			System.err.println(">>>>>>>>>>>>>>>>>>>저장성공");
		} else { // DB에 레코드가 있을때, 퇴근기록이 있을 경우, 새로 엔티티를 등록함.
			AttendanceEntity attendanceEntity = list.get(list.size() - 1); // 가장 최신의 출퇴근 레코드를 가져옴

			if (attendanceEntity.getGoWorkTime().getDayOfMonth() != LocalDateTime.now().getDayOfMonth()) {
				// 출근기록은 하루에 한번만 찍혀야 한다. DB의 레코드의 출근날짜와 오늘 시스템에서 가져온 날짜가 다를때만 새 출근기록이 등록된다.

				if (attendanceEntity.getLeaveWorkTime() != null) { // 퇴근기록이 있는경우, 새로운 출근기록을 등록한다.

					attendanceRepository.save(AttendanceEntity.builder()
							.employee(employeeEntityRepository.findById(mno).orElseThrow()).build());
					System.err.println(">>>>>>>>>>>>>>>>>>>저장성공");
				} else { // DB에 레코드가 있을때, 퇴근기록이 널일 경우, 이미 출근이 한번 눌려있는 상태를 의미함.
					System.err.println("이미 출근을 한번 누르셨습니다!!");
				}
			} else {

				System.err.println("같은 날짜에 두번 출근할 수 없습니다.");
			}
		}

		long aNo = findPk(mno);// 가장 마지막의 PK (Attendance)

		Optional<AttendanceEntity> result = attendanceRepository.findByNo(aNo);
		model.addAttribute("result", result.get());
	}

	@Override
	public long findPk(long mno) { // 로그인한 부대원의 가장 최근 출근데이터를 찾아주는 함수
		List<AttendanceEntity> list = attendanceRepository.findAllByEmployee_No(mno);
		long aNo = 0;
		if (!list.isEmpty()) {
			for (AttendanceEntity i : list) {
				aNo = i.getNo();
			}
		}
		System.err.println("<<<<<<<<<<<<<<<" + aNo);
		return aNo;
	}

	@Override // 퇴근 버튼을 눌렀을때, DB에 퇴근 시간이 기록되는 함수
	public void regLeaveTime(long mno) {
		// List<AttendanceEntity> result =
		// attendanceRepository.findByEmployee(employeeEntityRepository.findById(mno).orElseThrow());
		List<AttendanceEntity> result = attendanceRepository.findAllByEmployee_No(mno);
		if (!result.isEmpty()) {
			AttendanceEntity attendanceEntity = result.get(result.size() - 1);

			attendanceEntity.changeleaveWorkTime(LocalDateTime.now());

			attendanceRepository.save(attendanceEntity);
		}

	}

	@Override // 출퇴근 기록을 보여줄 함수.
	public void showInOut(long mno, Model model) {

		long aNo = findPk(mno);// 가장 마지막의 PK (Attendance)

		Optional<AttendanceEntity> result = attendanceRepository.findByNo(aNo);
		if (!result.isEmpty()) {
			ShowAttendanceListDTO showAttendanceListDTO = new ShowAttendanceListDTO(result.get());
			model.addAttribute("result", showAttendanceListDTO);
		}

	}

	@Override
	public void modalSelectGroup(long selectValue, Model model) {

		List<EmployeeEntity> resultList = new ArrayList<>();

		List<EmployeeEntity> list = employeeEntityRepository.findAll();
				

		if (!list.isEmpty()) { // DB에서 가져온 정보가 존재할때 아래 코드를 실행
			for (EmployeeEntity dto : list) {
				if (dto.getDepartment().getParent().getParent().getParent().getNo() == selectValue) {
					// 선택한 대대와 일치하는 부대원을 resultList에 새로 저장해주고, resultList를 결과화면에 뿌릴 생각
					resultList.add(dto);
				}
			}
		}

		model.addAttribute("employeeList", resultList);
	}

}