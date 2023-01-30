package com.green.nowon.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoarderInsertDTO {
    private long no;
    private String content;
    private String title;

    private long employeeNo;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private String writer ; // employeeName ;

    private long count;

    private String type;  //검색타입
    private String keyword;  // keyword

}
