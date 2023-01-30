package com.green.nowon.domain.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.green.nowon.domain.dto.DocumentDetailDTO;
import com.green.nowon.domain.dto.DocumentListDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class ElectronicDocumentEntity { //전자문서 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no; // pk


    @Column(nullable = false)
    private long docNo; //결제문서번호

    @Column(nullable = false)
    private String docTitle; //제목


    @CreationTimestamp
    private LocalDateTime docCreateDate;//상신일 ->결제올린일


    @UpdateTimestamp
    private LocalDateTime docUpdateDate;//결제 수정일

    @Column(nullable = false)
    boolean documentDelete; //삭제여부 1: 가입중 0: 탈퇴
/*
    @Column(nullable = false)
    private String pStatus; //결제상태 0:예정 1:대기 2:승인 3:반려
*/

    @Column(nullable = false)
    private String content; //삭제여부 1: 가입중 0: 탈퇴


    @CreationTimestamp
    private LocalDateTime docPayDate; //결제일
    
    @Column(nullable = false)//서류종류
    private String letter;
    
    @JoinColumn
    @ManyToOne
    private EmployeeEntity employee; //결제자 employee pk

    


    @Enumerated(EnumType.STRING)
    private PayStatus payStatus;
    //role이랑 비슷한개념


    public ElectronicDocumentEntity employyee(EmployeeEntity employee)
    {
        this.employee=employee;
        return this;

    }



    public ElectronicDocumentEntity updateStatus(PayStatus payStatus) {
        this.payStatus=payStatus;
        return this;
    }
}
