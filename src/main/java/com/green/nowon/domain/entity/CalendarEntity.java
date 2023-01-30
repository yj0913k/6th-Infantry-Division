package com.green.nowon.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class CalendarEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;

	@Column
	private LocalDateTime startDate;
	
	@Column
	private LocalDateTime endDate;
	
	@JoinColumn
	@ManyToOne
	private EmployeeEntity employee;
	
	@Enumerated(EnumType.STRING)
	private WorkStatus workStatus;
	
	
}
