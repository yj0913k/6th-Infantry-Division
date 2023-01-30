package com.green.nowon.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.AddressInfoDTO;
import com.green.nowon.domain.dto.YWEmployeeListDTO;
import com.green.nowon.domain.entity.AddressEntity;
import com.green.nowon.domain.entity.DepartmentEntity;
import com.green.nowon.repository.DeliveryEntityRepository;
import com.green.nowon.repository.DepartmentEmployeeEntityRepository;
import com.green.nowon.repository.DepartmentEntityRepository;
import com.green.nowon.service.DepartmentService;

@Service
public class DepartmentServiceProcess implements DepartmentService {

	@Autowired
	private DeliveryEntityRepository deliveryRepo;
	
	@Autowired
	private DepartmentEntityRepository repo;
	
	
	@Autowired
	DepartmentEntityRepository departmentRepo;//카테고리
	
	
	@Autowired
	DepartmentEmployeeEntityRepository departmentEmployeeRepo;
	
	@Override
	public void save(String[] departmentName, AddressInfoDTO dto) {
		// 조직도 등록페이지에서 입력받았던 대대 중대 소대 분대를 각각 깊이계층에 맞게 DB에 저장하는 로직
		//공통점은 Null일때 대체하는 객체를 리턴
		//orElse(): Null값이 아니라도 orElse내부가 실행
		//orElseGet() Null값일때만 orElseGet내부가 실행
		
		AddressEntity addressEntity = deliveryRepo.save(dto.toEntity());
		
		DepartmentEntity cate1=repo.findByParentNoAndName(null, departmentName[0])
				.orElseGet(()->repo.save(DepartmentEntity.builder().name(departmentName[0]).depth(1).parent(null).address(addressEntity).build()));

		DepartmentEntity cate2=repo.findByParentNoAndName(cate1.getNo(), departmentName[1])
				.orElseGet(()->repo.save(DepartmentEntity.builder().name(departmentName[1]).depth(2).parent(cate1).address(addressEntity).build()) );

		DepartmentEntity cate3=repo.findByParentNoAndName(cate2.getNo(), departmentName[2]) //부모차수가 2인 조건과 등록페이지의 세번째칸에 입력한 내용을 조건으로, 해당 데이터가 없다면 새로운 데이터(본인의 차수)를 생성해서 DB에 저장한다.
				.orElseGet(()->repo.save(DepartmentEntity.builder().name(departmentName[2]).depth(3).parent(cate2).address(addressEntity).build()) );

		DepartmentEntity cate4=repo.findByParentNoAndName(cate3.getNo(), departmentName[3])
				.orElseGet(()->repo.save(DepartmentEntity.builder().name(departmentName[3]).depth(4).parent(cate3).address(addressEntity).build()) );
	}

	@Override
	public boolean isReg(String text) {
		Optional<DepartmentEntity> result= repo.findByParentNoNullAndName(text);
		if(result.isEmpty())
			return true;
		return false;
	}
	
	//1차카테고리 리스트
	@Override
	public void fistDepartment(Model model) {
		model.addAttribute("list", repo.findByParentNoOrderByNameAsc(null));
		
	}

	@Override// 메인페이지의 왼쪽패널에서 조직도 트리뷰를 구현하기 위한 함수
	public void departmentList(Long parentNo, Model model) {
		
		if(parentNo.intValue()==0)parentNo=null;//1차카테고리의 경우, parentNo은 Null 이다. 
		model.addAttribute("list", repo.findByParentNoOrderByNameAsc(parentNo));
	}

	
	
	
	List<DepartmentEntity> cates;
	
	//재귀메서드
		private void categoryList(DepartmentEntity ca) {
		if(ca==null)return;
		cates.add(ca);
		categoryList(ca.getParent());
	}
	
		
	@Transactional
	@Override
	public void employeesOfDepartment(long cateNo, Model model) { 
		//commLayout의 조직도에서 대대~소대를 클릭했을 경우, 각각 조직범위에 속해있는 부대원을 보여줄 함수 //YWEmployListDTO를 사용함
		
		
		//cates는 조직도 조회페이지에서 부대원들을 보여줄 떄, 상위 탭에서 HOME> 대대> 중대 > 소대> 분대를 보여주기 위한 함수. 
		cates=new ArrayList<>();
		categoryList(departmentRepo.findById(cateNo).get()); //바로 위의 categoryList(DepartmentEntity ca) 함수를 재귀적으로 호출함.
		model.addAttribute("cates", cates);
		
		
		// 각 해당하는 조직단위에 속해있는 부대원들을 화면에 뿌려주기 위해 사용하는 함수
		model.addAttribute("DepartmentViewlist", departmentEmployeeRepo.findAllByDepartmentNo(cateNo)
				.stream()
				.map(YWEmployeeListDTO::new) 
				.collect(Collectors.toList()));
	}

}
