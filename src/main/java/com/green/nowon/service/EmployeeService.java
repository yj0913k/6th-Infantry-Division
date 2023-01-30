package com.green.nowon.service;

import com.green.nowon.domain.dto.EmployeeInsertDTO;
import com.green.nowon.domain.dto.EmployeeListDTO;
import com.green.nowon.domain.dto.RankListDTO;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


public interface EmployeeService {




    Map<String, String> fileTempUpload(MultipartFile gimg);

	void save(EmployeeInsertDTO dto);

    List<RankListDTO> show(Model model);


    List<EmployeeListDTO> showList(Model model); //회원목록페이지에서 내용 뿌려주기


    String getRank(String no);

    void updateMember(String number, String position);

}
