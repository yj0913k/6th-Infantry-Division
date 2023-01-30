package com.green.nowon.domain.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.nowon.domain.entity.DepartmentEntity;
import com.green.nowon.domain.entity.EmployeeEntity;
import com.green.nowon.domain.entity.EmployeeImgEntity;
import com.green.nowon.domain.entity.RankEntity;
import com.green.nowon.security.MyRole;
import com.green.nowon.util.FileUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EmployeeInsertDTO {
    private long no;
    private String email; //이메일
    private String name; //이름
    private String pass; //비밀번호
    private String joinDate; //입대일

    private String birth;
    private long[] departmentNo;

    private boolean deleted; //삭제여부 1:가입중 0: 탈퇴
    private String phone;

    //군번
    private String number; //군번
    //주소

    //계급번호

    private long position; //rank_no


    //소속번호
    private long department; //소속번호

    private String role;


    //private List<EmployeeImgDTO> imgs;

    private String[] newName;
    private String[] orgName;

    public List<EmployeeImgEntity> toEmployeeListImgs(EmployeeEntity employee,String url){
        List<EmployeeImgEntity> imgs= new ArrayList<>();
        for(int i=0; i<orgName.length; i++) {
            if(orgName[i].equals("") || orgName[i]==null)continue;

            boolean defImg = false;
            if(i==0)defImg= true;

            EmployeeImgEntity ent=EmployeeImgEntity.builder()
                    .url(url)
                    .orgName(orgName[i])
                    .newName(newName[i])
                    .defImg(defImg)
                    .employee(employee)
                    .build();
            imgs.add(ent);
        }
        FileUtils.moveUploadLocationFromTemp(newName, url);
        return imgs;
    }



    //소속번호



    private List<EmployeeImgDTO> imgs;

    private String type;
    private String keyword;

    public EmployeeInsertDTO() {

    }


    public EmployeeEntity toEntity(PasswordEncoder pe) {
        return EmployeeEntity.builder()
                .no(no)
                .email(email)
                .name(name)
                .pass(pe.encode(pass))
                .joinDate(joinDate)
                .phone(phone)
                .number(number)
                .birth(birth)
                .rank(RankEntity.builder().no(position).build())
                .department(DepartmentEntity.builder().no(departmentNo[3]).build())
                .build()
                .addRole(MyRole.valueOf(role));

    }

}