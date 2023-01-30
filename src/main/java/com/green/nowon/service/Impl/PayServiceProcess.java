package com.green.nowon.service.Impl;

import com.green.nowon.domain.dto.*;

import com.green.nowon.domain.entity.EmployeeEntity;
import com.green.nowon.domain.entity.RankEntity;
import com.green.nowon.repository.*;
import com.green.nowon.service.PayService;
import com.green.nowon.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PayServiceProcess implements PayService {
    @Value("${file.location.temp}")
    private String locationTemp;

    @Value("${file.location.upload}")
    private String locationUpload;

    @Autowired
    private RankEntityRepository rankEntityRepository;
    @Autowired
    private RankImgRepository rankImgRepository;
    @Autowired
    private RankImgEntityRepository rankImgEntityRepository;

    @Autowired
    private EmployeeEntityRepository employeeEntityRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private PayEntityRepository payEntityRepository;

    @Transactional
    @Override
    public void rankPay(RankInsertDTO dto) {
        RankEntity entity = rankEntityRepository.save(dto.toRankEntity());

        dto.toRankListImgs(entity, locationUpload).forEach(rankImgEntityRepository::save);

    }
    
    @Transactional
    @Override
    public void pay(Model model) {
    	model.addAttribute("pList",employeeEntityRepository.findAll()
    			.stream()
    			.map(PayEmployeeListDTO::new)
    			.collect(Collectors.toList()));
    	
    	model.addAttribute("list",rankEntityRepository.findAll()
    			.stream()
    			.map(RankListDTO::new)
    			.collect(Collectors.toList()));


    }
    


    @Override
    public Map<String, String> fileTempUpload(MultipartFile gimg) {
        return FileUtils.fileUpload(gimg, locationTemp);
    }

    @Transactional
    @Override //급여 디테일 ajax
    public void payDetail(long noId, Model model) {

       model.addAttribute("detail" ,  employeeEntityRepository.findById(noId)
               .map(PaytDetailEmployeeListDTO::new)
                       .get()
               );

        List<PayAttendanceRegviewDTO> list = attendanceRepository.findAllByEmployee_No(noId)
                .stream()
                .map(PayAttendanceRegviewDTO::new)//map = 모아서 저장한다
                .collect(Collectors.toList());//collect = 생성자를 맵 형태로 모은다
        long total=0 ;
        for (PayAttendanceRegviewDTO dto : list) {
            total += dto.getOverTime();

        model.addAttribute("total" , total);

        }
        model.addAttribute("detailattendance",list );



    }

    @Override
    public void paysave(PaySaveDTO dto, long no) {
        payEntityRepository.save(dto.toEntity().employee(EmployeeEntity.builder().no(no).build()));
    }

    @Override
    public void payslip(Model model, long no) {
        model.addAttribute("payslip" , payEntityRepository.findAllByEmployee_no(no)
                .stream().map(PaySlipListDTO::new).collect(Collectors.toList()));
    }

    @Override
    public void detailpay(Model model, long no) {
        model.addAttribute("paydetail", payEntityRepository.findById(no)
                .map(PayDetailDTO::new).orElseThrow());
    }


    @Override
	public void pay(String email, Model model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PaySlipListDTO> search(String keyword) {
		return payEntityRepository.findByAccountContaining(keyword)
				.stream()
				.map(PaySlipListDTO::new)
				.collect(Collectors.toList());
	}


}
