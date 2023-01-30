package com.green.nowon.controller;

import com.green.nowon.domain.dto.BoarderInsertDTO;
import com.green.nowon.domain.dto.EmployeeInsertDTO;
import com.green.nowon.domain.dto.EmployeeListDTO;
import com.green.nowon.domain.dto.RankListDTO;
import com.green.nowon.domain.entity.EmployeeEntity;
import com.green.nowon.domain.entity.RankEntity;
import com.green.nowon.mapper.BoardMapper;
import com.green.nowon.repository.EmployeeEntityRepository;
import com.green.nowon.repository.EmployeeListRepository;
import com.green.nowon.repository.RankEntityRepository;
import com.green.nowon.security.MyUserDetails;
import com.green.nowon.service.AttendanceService;
import com.green.nowon.service.DepartmentService;
import com.green.nowon.service.EmployeeService;
import com.green.nowon.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    BoardMapper mapper;

    @Autowired
    private EmployeeService service;

    @Autowired
    private AttendanceService attendanceService;

	@Autowired
	private DepartmentService departmentService;

    @Autowired
    private RankService rankService;


    @Autowired
    private EmployeeEntityRepository employeeEntityRepository;
    @Autowired
    private EmployeeListRepository employeeListRepository;


//    @GetMapping("/member/list") //간부,병사 리스트
//    public String memberList(Model model) {
//        service.showList(model);
//        return "member/memberList";
//    }

    //index페이지 들어갈 경우 myuserdetails로 자기 정보 뿌려줌/

//    @GetMapping("/") //메인페이지 접속시 자료 가져온다.
//    public String main(Model model, @AuthenticationPrincipal MyUserDetails myUserDetails){
//        String name= myUserDetails.getName();
//        String email = myUserDetails.getEmail();
////        String rank = myUserDetails.getRank();
////        String imgs = myUserDetails.getImgs().toString();
////        String department = myUserDetails.getDepartment();
////        System.out.println(department);
////        model.addAttribute("department", department);
//        return "index";
//    } 구버전




    @GetMapping("/member/move") //간부,병사 인사발령
    public String memberMove(Model model) {
        service.show(model);
        return "member/memberMove";
    }
    @ResponseBody
    @GetMapping("/member/move/{no}") //간부,병사 인사발령
    public String memberMove(@PathVariable String no) {
        String position =service.getRank(no);

        return position;
    }

    @GetMapping("/") //메인페이지 접속시 자료 가져온다.
    public String main(Model model, @AuthenticationPrincipal MyUserDetails myUserDetails){
        String name= myUserDetails.getName();
        String email = myUserDetails.getEmail();
        List<BoarderInsertDTO> result= mapper.findAll();
        model.addAttribute("list", result);
        //건우님꺼
        long mno = myUserDetails.getMno();
        attendanceService.showInOut(mno, model);

        //
        String rank = myUserDetails.getRank();

//        String department = myUserDetails.getDepartment();
//        model.addAttribute("department", department);
        attendanceService.showAttendEmployee(model);
        departmentService.employeesOfDepartment(1L, model);

        //영욱씨꺼

        return "index";
    }

    @ResponseBody
    @PostMapping("/member/moveMember")
    public void movemove(String number, String position){//인사이동 계급 변경
        service.updateMember(number, position);
//        service.updatePosition(number, position);
    }

    @PostMapping("/signUp") //회원가입페이지(자료 넘기기)
    public String signUp(EmployeeInsertDTO dto) {
        service.save(dto);
        return "redirect:/member/registration";
    }
    //service. serviceprocess 구분의 이유: 여러가지 serviceprocess를  service에 연결 가능
    //이 후 service, serviceporcess 생성
    //service는 가교 역할.
    //컨트롤러에서 service . @@@@  넣어줌
    // @@@@ 이거를 service에 생성 해주고
    // @@@@ process에서 umimplement... 해서 생성!(여기서 리턴값은 필요 없다. 왜냐. 컨트롤러에서 반환값을 정의 해주니까.
    //dto 생성 후 넘겨줄 리스트 det에 만들어준다.
    //그다음 serviceprocess로 간다
    //serviceprocess 에서
//    @Override
//    public void save(EmployeeInsertDTO dto, Model model) {
//        employeeEntityRepository.save(dto.toEntity()); // db로 데이터가 저장됨.(즉. 회원가입의 본질)
//        model.addAttribute("list", dto );     // html 화면에 dto값들이 리스트를 타고 넘어 온다.
//    }

    @Transactional //검색페이지 들어갈 경우 실행 됨
    //shoList으로 값을 뿌려줌
    @GetMapping("/member/list")
    public String list(Model model,@PageableDefault(size = 1) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchName) {
//        Page<EmployeeEntity> showList= employeeListRepository.findAll(pageable);
//        Page<EmployeeEntity> showList= employeeListRepository.findByNameContaining(searchName, pageable);
        Page<EmployeeEntity> paging= employeeListRepository.findByNameContaining(searchName, pageable);
        Page<EmployeeListDTO> showshow=paging.map(EmployeeListDTO::new);
        System.err.println(paging.getNumber());
        System.err.println(paging.getTotalPages());
        int startPage = (int) (Math.floor(paging.getNumber()/3)*3+1);
        int endPage = startPage+2 < paging.getTotalPages()?startPage+2:paging.getTotalPages();

        ////////////////////
        //////////////////
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("paging", showshow);
        model.addAttribute("searchName", searchName);
        return "member/memberList";
    }
    // 회원목록 검색 구현



    ////////////////////////////////////////////ajax 시도//////


}



