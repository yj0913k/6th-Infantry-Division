package com.green.nowon.controller;

import com.green.nowon.domain.dto.DocumentInsertDTO;
import com.green.nowon.domain.dto.DocumentListDTO;
import com.green.nowon.security.MyUserDetails;
import com.green.nowon.service.DocumentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DocumentController {
	@Autowired
	private DocumentService service;

    @Autowired

	@GetMapping("/document")
	public String document() {

		return "document/document";
	}

	@PostMapping("/document")
	public String documentInser( DocumentInsertDTO dto , @AuthenticationPrincipal MyUserDetails myUserDetails){
		long eno = myUserDetails.getMno();
		service.documentInsert(dto,eno);
		return "redirect:/document";
	}


	//결재요청 진행 현황으로 이동c  //자기만보는거
	@GetMapping("/document/process")
	public String documentProcess(Model model, @AuthenticationPrincipal MyUserDetails myUserDetails) {
		service.standview(model, myUserDetails.getMno());
		return "document/document-process";
	}


	//결재요청 진행 현황의 상세페이지
	@GetMapping("/document/process/detail/{no}")
	public String documentProcessDetail(Model model ,@PathVariable long no )
	{
		service.detailstand(model,no);
		return "document/document-process-detail";
	}


	//결재요청 대기 페이지로 이동
	@GetMapping("/document/stand") //대기함
	public String documentStand(Model model) {
		service.standview2(model);


		return "document/document-stand";
	}
	//결제요청 대기 페이지의 상세페이지 이동
	@GetMapping("/document/stand/detail/{no}")
	public String documentStandDetail(Model model , @PathVariable long no) {
		service.detailstand(model,no);
		return "document/document-stand-detail";
	}
	
	//결재대기페이지 검색기능
	@GetMapping("/document/search")
	public String search(@RequestParam(value = "keyword") String keyword,Model model) {
		List<DocumentListDTO> searchList = service.search(keyword);
		model.addAttribute("searchList",searchList);
		return "document/document-search"; 
	}

	@PostMapping("/document/stand/detail/approval/{no}")
	public String documentStandDetail(@PathVariable long no) {
		service.updateStandStatus(no);
		return "redirect:/document/stand";
	}


	@PostMapping("/document/stand/detail/up/{no}")
	public String documentStandDetail2(@PathVariable long no) {
		service.updateCanclestatus(no);
		return "redirect:/document/stand";
	}
}
