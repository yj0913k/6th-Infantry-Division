package com.green.nowon.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PayStatus {



    보류("STATUS_WAITING"),//1 대기

    승인("STATUS_WAITING"),//2 승인

    반려("STATUS_COMPANION"); //3 반려



    private final String status;// getStatus() //
}
