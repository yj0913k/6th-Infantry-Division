package com.green.nowon.domain.entity;

import javax.persistence.Column;
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
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class DepartmentEntity { //소속 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

    @Column(nullable = false)
    private String name; //소속이름

    @Column(nullable = false)
    private int depth; //차수
    @JoinColumn
    @ManyToOne
    private DepartmentEntity parent; //상위카테고리 소속

    @JoinColumn
    @ManyToOne
    private AddressEntity address; //소속에대한 주소
}
