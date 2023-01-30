package com.green.nowon.domain.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class ElectronicDocumentAgreementEntity {
    //누군가가 결제문서를 올리면
    //다른 누군가가 결제문서에대한 승인 또는 반대  대기 에대한 db


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;  //합의번호

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime agreementDay; // 합의일

    @JoinColumn
    @ManyToOne
    private EmployeeEntity employee; //결제자 employee fk


    @JoinColumn
    @OneToOne
    private ElectronicDocumentEntity ed; //전자문서 Fk
    @Builder.Default  //결제상태를 enum으로
    @CollectionTable(name = "my_agreementstatus")
    @Enumerated(EnumType.STRING)//설정하지 않으면 숫자(ORDINAL)
    @ElementCollection(fetch = FetchType.EAGER)//1:N 즉시로딩
    private Set<AgreementStatus> agreementStatus=new HashSet<>();
    //role이랑 비슷한개념
    public ElectronicDocumentAgreementEntity addAgreementStatus(AgreementStatus status) {
        agreementStatus.add(status);
        return this;
    }

}
