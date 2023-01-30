package com.green.nowon.service;

import java.util.List;
import java.util.Map;

import com.green.nowon.domain.dto.DocumentListDTO;
import com.green.nowon.domain.dto.PaySaveDTO;
import com.green.nowon.domain.dto.PaySlipListDTO;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.RankInsertDTO;

public interface PayService {

	void rankPay(RankInsertDTO dto);

	void pay(String email, Model model);

	void pay(Model model);

	Map<String, String> fileTempUpload(MultipartFile gimg);

	void payDetail(long noId, Model model);

	void paysave(PaySaveDTO dto, long no);

	void payslip(Model model, long no);

	void detailpay(Model model, long no);

	List<PaySlipListDTO> search(String keyword);
}
