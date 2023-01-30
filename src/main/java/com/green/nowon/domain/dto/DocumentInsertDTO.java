package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.ElectronicDocumentEntity;
import com.green.nowon.domain.entity.EmployeeEntity;
import com.green.nowon.domain.entity.PayStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DocumentInsertDTO {
    private long no; //pk
    private long docNo; //결제문서번호
    private String content; //내용

    private String letter;

    private LocalDateTime docCreatedDate;//상신일

    private LocalDateTime payCreatedDate; //결제일

    private long eno;
    private String docTitle; //제목

    public ElectronicDocumentEntity toEntity(){
          return ElectronicDocumentEntity.builder()
                  .no(no)
                  .docNo(docNo)
                  .docTitle(docTitle)
                  .content(content)
                  .docCreateDate(docCreatedDate)
                  .docPayDate(payCreatedDate)
                  .letter(letter)
                  .payStatus(PayStatus.보류)
                  .build();

    }


}

