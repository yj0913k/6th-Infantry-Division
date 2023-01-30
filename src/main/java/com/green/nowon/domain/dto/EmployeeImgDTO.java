package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.EmployeeEntity;
import com.green.nowon.domain.entity.EmployeeImgEntity;
import lombok.Getter;

@Getter
public class EmployeeImgDTO {
    private long no;
    private String orgName;
    private String newName;
    private String url;
//    private boolean defImg;

    private String imgUrl;

    public EmployeeImgDTO(EmployeeEntity e) {

    }
}
