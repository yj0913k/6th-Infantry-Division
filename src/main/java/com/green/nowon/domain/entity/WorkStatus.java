package com.green.nowon.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WorkStatus {


    비번("STATUS_NONWATCHDUTY"),//0 비번
    당직("STATUS_WATCHDUTY"),//1 당직
    휴가("STATUS_VACATION"), //2 휴가
	병가("STATUS_SICK"); //3 병가



    private final String status;// getStatus() //
}
