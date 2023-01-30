package com.green.nowon.service.Impl;

import com.green.nowon.domain.dto.DocumentDetailDTO;
import com.green.nowon.domain.dto.DocumentInsertDTO;
import com.green.nowon.domain.dto.DocumentListDTO;
import com.green.nowon.domain.entity.ElectronicDocumentEntity;
import com.green.nowon.domain.entity.EmployeeEntity;
import com.green.nowon.domain.entity.PayStatus;
import com.green.nowon.repository.ElectronicDocumentEntityRepository;
import com.green.nowon.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceProcess implements DocumentService {

    @Autowired
    private ElectronicDocumentEntityRepository electronicDocumentEntityRepository;
    @Override
    public void documentInsert(DocumentInsertDTO dto, long eno) {
        electronicDocumentEntityRepository.save(dto.toEntity().employyee(EmployeeEntity.builder()
                        .no(eno)
                .build()));

    }

    @Override
    public void standview(Model model, long mno) {
        model.addAttribute("stand" , electronicDocumentEntityRepository.findAllByEmployee_no(mno)
                .stream().map(DocumentListDTO::new).collect(Collectors.toList()));

    }

    @Override
    public void detailstand(Model model, long no) {
        model.addAttribute("detail", electronicDocumentEntityRepository.findById(no)
                .map(DocumentDetailDTO::new).orElseThrow());

    }

    @Transactional
    @Override
    public void updateStandStatus(long no) {
       ElectronicDocumentEntity entity =  electronicDocumentEntityRepository.findById(no)
                 .map(e->e.updateStatus(PayStatus.승인))
                 .orElseThrow();
         System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>"+entity);
    }

    @Transactional
    @Override
    public void updateCanclestatus(long no) {
        ElectronicDocumentEntity entity =  electronicDocumentEntityRepository.findById(no)
                .map(e->e.updateStatus(PayStatus.반려))
                .orElseThrow();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>"+entity);
    }

    @Override
    public void standview2(Model model) {
        model.addAttribute("stand" , electronicDocumentEntityRepository.findAll()
                .stream().map(DocumentListDTO::new).collect(Collectors.toList()));
    }


	@Override
	public List<DocumentListDTO> search(String keyword) {
		return electronicDocumentEntityRepository.findByLetterContaining(keyword)
				.stream()
				.map(DocumentListDTO::new)
				.collect(Collectors.toList());
				
		
		
	}


	


}
