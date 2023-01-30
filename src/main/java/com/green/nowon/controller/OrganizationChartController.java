package com.green.nowon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.domain.dto.AddressInfoDTO;
import com.green.nowon.service.DepartmentService;
import com.green.nowon.service.AddressService;

@Controller
public class OrganizationChartController {

	@Autowired
	//private CategoryService service;
	private DepartmentService dservice;
	
	
	@Autowired
	private AddressService Oservice;
	
	
	//commLayout의 왼쪽패널에서 조직등록을 클릭시 URL을 날려줌
	@GetMapping("/registerOrganizationChart")
	public String RegisterOrganizationChart() {
		return "organizationChart/registerOrganizationChart";
	}
	
	
	//조직도 등록페이지에서 입력한 대대~분대 내용과 주소정보를 DB에 저장해주는 함수
	@ResponseBody
	@PostMapping("/user/delivery")
	public String deliveryInfo(String[] departmentName, AddressInfoDTO dto) { //@AuthenticationPrincipal MyUserDetails userDetails) {
		dservice.save(departmentName, dto);
		return "OrganizationChart/registerOrganizationChart";
	}
	
	//commLayout의 조직도에서 대대~소대를 클릭했을 경우, 각각 조직범위에 속해있는 부대원을 보여줄 함수 //YWEmployListDTO를 사용함
	@GetMapping("/common/category/{no}/goods")
	public String employeesOfDepartment(@PathVariable long no, Model model) {
		dservice.employeesOfDepartment(no, model);
		return "organizationChart/chartMemberList";
	}
	
	
	// 시작화면의 왼쪽패널에서 조직도를 트리뷰를 구현하기 위한 함수 
	// 조직도의 카테고리를 가져오기 위한 컨트롤러
	// commLayout.html 페이지에서 url을 호출함
	@GetMapping("/common/layout/categorys/{parentNo}")
	public String category(@PathVariable long parentNo, Model model) {
		//service.categoryList(parentNo, model);
		dservice.departmentList(parentNo, model);
		return "organizationChart/ol-category"; //조직도 왼쪽패널 트리뷰를 재귀적으로 html로 생성해주는 펭지ㅣ
	}
	
	
	
	//admin의 등록페이지
	@GetMapping("/common/categorys") //사원등록 페이지의 조직도 셀렉트 박스를 선택시 호출되는 함수.
	public String fistCategory(Model model) {
		dservice.fistDepartment(model);
		return "category/category";
	}
	
	@GetMapping("/common/categorys/{parentNo}")
	public String categoryList(@PathVariable long parentNo,Model model) {
		dservice.departmentList(parentNo, model);
		return "category/category";
	}

	
	
}
