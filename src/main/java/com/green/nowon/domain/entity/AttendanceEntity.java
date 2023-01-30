package com.green.nowon.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@EntityListeners(value= {AuditingEntityListener.class})
public class AttendanceEntity { //근태관리
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

	@CreationTimestamp
	@Column
    private LocalDateTime goWorkTime; //출근시간
    
    //@UpdateTimestamp //이걸넣어야할지 말아야할지를 .. 고민중입니다 .
	//@LastModifiedDate
    @Column
    private LocalDateTime leaveWorkTime; //퇴근시간  //시스템의 시간을 받아와라 이건거같습니다! LocalTime.now();
    //시스템에서 찍어서 가져와야한다 !
    
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate attDate; //근무 일자 LocalDate currentDate = LocalDate.now();  결과값 = 2020-09-23
    // 이표현이 맞는지를 모르겠습니다  https://footprint-of-nawin.tistory.com/67

    @JoinColumn
    @ManyToOne
    private EmployeeEntity employee; //employee_no fk

    @Column
    @ColumnDefault("false")
    private boolean watchDuty;
    
    public AttendanceEntity employee(EmployeeEntity e) {
    	this.employee=e;
    	return this;
    }

    public void changeleaveWorkTime(LocalDateTime time) {
    	this.leaveWorkTime = time;
    }
    
    public void changeWatchDuty(boolean watchDuty) {
    	this.watchDuty=watchDuty;
    }
}

