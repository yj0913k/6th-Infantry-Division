package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.AttendanceEntity;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class PayAttendanceRegviewDTO {
    private long no;

    private LocalDateTime goWorkTime;

    private LocalDateTime leaveWorkTime;

    private LocalDate attDate;

    private String name;

    private long overTime;

    private long total ;


    /*
     * public AttendanceRegDTO(AttendanceEntity a) { this.no = a.getNo();
     * this.goWorkTime = a.getGoWorkTime(); this.leaveWorkTime =
     * a.getLeaveWorkTime(); }
     */




    /*
     * public AttendanceRegDTO(AttendanceEntity a) { this.no=a.getNo();
     * this.goWorkTime = a.getGoWorkTime(); this.leaveWorkTime =
     * a.getLeaveWorkTime(); this.attDate = a.getAttDate(); }
     */
    /**
     *
     * @param a 근태 테이블
     * AttendanceRegViewDTO = 근태 저장 정보를 보여주는 DTO 생성자
     */
    public PayAttendanceRegviewDTO(AttendanceEntity a) {
        LocalDateTime base=LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 00 , 00));
        LocalTime baseTime=base.toLocalTime();

        this.no=a.getNo();
        this.goWorkTime = a.getGoWorkTime();
        this.leaveWorkTime = a.getLeaveWorkTime();
        this.attDate = a.getAttDate();
        this.name = a.getEmployee().getName();



        Duration overTime = Duration.between(  baseTime,this.leaveWorkTime.toLocalTime() );
        if(overTime.toHours()> 0) {
            this.overTime = overTime.toHours();
        }
        else{
            this.overTime = 0;
        }




    }




}
