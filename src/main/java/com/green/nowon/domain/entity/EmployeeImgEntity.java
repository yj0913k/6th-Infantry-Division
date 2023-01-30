package com.green.nowon.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class EmployeeImgEntity { //유저이미지

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no; //pk
    private String orgName; //사진 오리지널이름
    private String newName; //새로운 이름
    private String url; //이미지 주소
    private boolean defImg; //대표이미지

    @JoinColumn
    @ManyToOne //rank_no
    private EmployeeEntity employee;

}