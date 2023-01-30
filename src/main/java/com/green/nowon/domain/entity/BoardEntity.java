package com.green.nowon.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

    @Column(nullable = false)
    private String title; //제목
    @CreationTimestamp
    private LocalDateTime createdDate; //작성일  // default = current timestamp

    @UpdateTimestamp
    private LocalDateTime updatedDate; //수정일// default = current timestamp + on

    @Column(nullable = false)
    private String content; //내용

    @Column(nullable = false)
    private String writer;

    //조회수 넣을까요?
    @Column(nullable = true)
    private long  count;


    @JoinColumn
    @ManyToOne
    private EmployeeEntity employee;

}
