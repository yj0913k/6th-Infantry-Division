package com.green.nowon.service.Impl;

import com.green.nowon.domain.dto.EmployeeInsertDTO;
import com.green.nowon.domain.dto.EmployeeListDTO;
import com.green.nowon.domain.dto.RankListDTO;
import com.green.nowon.domain.dto.ShowAttendanceListDTO;
import com.green.nowon.domain.entity.AttendanceEntity;
import com.green.nowon.domain.entity.DepartmentEmployeeEntity;
import com.green.nowon.domain.entity.EmployeeEntity;
import com.green.nowon.domain.entity.RankEntity;
import com.green.nowon.repository.*;
import com.green.nowon.service.EmployeeService;
import com.green.nowon.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceProcess implements EmployeeService {

    @Value("${file.location.temp}")
    private String locationTemp;

    @Value("${file.location.upload}")
    private String locationUpload;
    @Autowired
    private EmployeeEntityRepository employeeEntityRepository; //이거는 유저.

    @Autowired
    private DepartmentEntityRepository departmentEntityRepo;

    @Autowired
    private DepartmentEmployeeEntityRepository departmentEmployeeEntityRepo;

    @Autowired
    private RankEntityRepository rankEntityRepository;

    @Autowired
    private EmployeeImgEntityRepository employeeImgEntityRepository;

    // @Override
    //setter : 값을 넣을때
    //getter : 나머지는 얘가 다 해줌
    // public void save() {
//        employeeEntityRepository.save();
//    }

    @Autowired
    private PasswordEncoder pe;


	/*
	 * @Override public void save(EmployeeInsertDTO dto, Model model) { long[]
	 * departmentNo = dto.getDepartmentNo();
	 *
	 * EmployeeEntity employeeEntity =
	 * employeeEntityRepository.save(dto.toEntity(pe));
	 *
	 *
	 * for(long no:departmentNo) {
	 * departmentEmployeeEntityRepo.save(DepartmentEmployeeEntity.builder()
	 * .employee(employeeEntity)
	 * .department(departmentEntityRepo.findById(no).get()) .build());
	 *
	 * }
     }
     */


    @Override
    public Map<String, String> fileTempUpload(MultipartFile gimg) {

//        amazonS3ResourceUtil.store(gimg); 나중에 아마존 연결 하면 실행


        return FileUtils.fileUpload(gimg, locationTemp);
    }

    @Override //유저등록
    public void save(EmployeeInsertDTO dto) {
        EmployeeEntity entity = employeeEntityRepository.save(dto.toEntity(pe));
        dto.toEmployeeListImgs(entity, locationUpload).forEach(employeeImgEntityRepository::save);


        long[] departmentNo = dto.getDepartmentNo();

        for (long no : departmentNo) {
            departmentEmployeeEntityRepo.save(DepartmentEmployeeEntity.builder()
                    .employee(entity)
                    .department(departmentEntityRepo.findById(no).get())
                    .build());
        }

    }



    @Transactional // 인사발령 페이지
    @Override
    public List<RankListDTO> show(Model model) {
        model.addAttribute("rList", rankEntityRepository.findAll()
                .stream()
                .map(RankListDTO::new)
                .collect(Collectors.toList()));
        return null;
    }

    @Transactional
    @Override
    public List<EmployeeListDTO> showList(Model model) {
        model.addAttribute("showList", employeeEntityRepository
                .findAll().stream()
                .map(EmployeeListDTO::new).collect(Collectors.toList()));
        return null;
    }

    @Transactional
    @Override
    public String getRank(String no) {
        Optional<EmployeeEntity> rankNo = employeeEntityRepository.findByNumber(no);
        String rankPosition = rankNo.get().getRank().getPosition();
//        System.err.println(rankPosition);
        return rankPosition;
                //rankEntityRepository.findById(rankNo).get().getPosition();
    }


    @Transactional
    @Override
    public void updateMember(String number, String position) {
        Optional<EmployeeEntity> po = employeeEntityRepository.findByNumber(number);
//        System.out.println(position);
//        System.err.println(rankEntityRepository.findByPosition(position).get().getNo());
        //po.get() ->> employee
        //po.get().getrank() ->
        //po.get().getrank().set
        po.get().setRank(rankEntityRepository.findByPosition(position).get());
    }

    /* search */
    @Transactional
    public List<EmployeeListDTO> search(String keyword) {
        return employeeEntityRepository.findByNameContaining(keyword)
                .stream()
                .map(EmployeeListDTO::new)
                .collect(Collectors.toList());
    }




}




