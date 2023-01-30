package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.ElectronicDocumentEntity;
import com.green.nowon.domain.entity.PayStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class DocumentDetailDTO {

    private long no;
    private long docNo;
    private String title;
    private String department;

    private String docCreatedDate;

    private String name;  //신청자

    private String content;
    private String letter;
    private PayStatus payStatus;


    public DocumentDetailDTO(ElectronicDocumentEntity e) {
        this.no = e.getNo();
        this.docNo = e.getDocNo();
        this.title = e.getDocTitle();
        this.department = e.getEmployee().getDepartment().getName();
        this.docCreatedDate = e.getDocCreateDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        this.name = e.getEmployee().getName();
        this.content=e.getContent();
        this.letter=e.getLetter();
        this.payStatus=e.getPayStatus();
    }
}
