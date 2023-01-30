package com.green.nowon.controller;

import com.green.nowon.domain.dto.PaySaveDTO;
import com.green.nowon.domain.dto.PaySlipListDTO;
import com.green.nowon.domain.dto.RankInsertDTO;
import com.green.nowon.security.MyUserDetails;
import com.green.nowon.service.PayService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

//@RequiredArgsConstructor
@Controller
public class PayController {
	//0112급여 수정 전 커밋
	@Autowired
	private PayService service;

	@ResponseBody //ajax 데이터값만 반환시키는 
	@PostMapping("/admin/temp-upload") //file업로드
	public Map<String, String> tempUpload(MultipartFile gimg) {
		System.out.println("원본사진이름>>>>>>>" + gimg.getOriginalFilename());
		return service.fileTempUpload(gimg);
	}

	//급여등록 페이지로 이동

	@GetMapping("/pay")
	public String pay(Model model) {
		service.pay(model);
		return "pay/pay";
	}
    //급여등록
	@PostMapping("/pay/save")
	public String save(PaySaveDTO dto ,@AuthenticationPrincipal MyUserDetails myUserDetails) {
		    long no = myUserDetails.getMno();
     		service.paysave(dto,no);

			return "redirect:/pay";
		}
	 @GetMapping("/pay/detail")
	public String detailPay(Model model, @AuthenticationPrincipal MyUserDetails myUserDetails) {
		 long no = myUserDetails.getMno();
		   service.payslip(model,no);
			return "pay/payslip";
	 }

	 @GetMapping("/payslip/detail/{no}")
	 public String detailPay(Model model, @PathVariable long no){
		service.detailpay(model,no);
		return "pay/detail-pay";
	 }


	@GetMapping("/pay/detail/{noId}")
	public String payDetail(@PathVariable long noId, Model model) {
		service.payDetail(noId, model);
        return "pay/pay-detail";
    }

	@GetMapping("/pay/search")
	public String search(@RequestParam(value = "keyword") String keyword,Model model) {
		List<PaySlipListDTO> searchList = service.search(keyword);
		model.addAttribute("searchList",searchList);
		return "pay/pay-search";
	}






}
