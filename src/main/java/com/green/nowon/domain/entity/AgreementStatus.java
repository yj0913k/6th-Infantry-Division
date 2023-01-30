package com.green.nowon.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AgreementStatus {


    WAITING("STATUS_WAITING"),//0 대기

    AGREEMENT("STATUS_AGREEMENT"),//1 합의
    OPPOSITION("STATUS_OPPOSITION"); //2 반대



    private final String status;// getStatus() //
}
