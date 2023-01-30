package com.green.nowon.service;


import com.green.nowon.domain.dto.DocumentInsertDTO;
import com.green.nowon.domain.dto.DocumentListDTO;

import java.util.List;

import org.springframework.ui.Model;

public interface DocumentService {
    void documentInsert(DocumentInsertDTO dto, long eno);

    void standview(Model model, long mno);

    void detailstand(Model model, long no);

    void updateStandStatus(long no);

    void updateCanclestatus(long no);

    void standview2(Model model);

	List<DocumentListDTO> search(String keyword);

}
