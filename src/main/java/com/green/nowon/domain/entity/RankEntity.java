package com.green.nowon.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class RankEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no; // pk
    @Column(nullable = false)
    private String position; //계급
    @Column(nullable = false)
    private long basePay; //기본급
    @Column(nullable = false)
    private long nightPay; //당직급여
    @Column(nullable = false)
    private long overPay; //초과 급여

    //연차수당할시 휴가 db만들어줘야한다.
    @Column(nullable = false)
    private long annualPay; //연차 급여
    @Column(nullable = false)
    private int annualDay; //연차일수


    //계급이미지
    @Builder.Default
    @OneToMany(mappedBy = "rank")
    private List<RankImgEntity> imgs=new ArrayList<>();

    public RankImgEntity defImg() {
        for(RankImgEntity img:imgs) {
            if(img.isDefImg()) return img;

        }
        return imgs.get(0);//만약에 대표이지미지 없으면 첫번째이미지로
    }


}

