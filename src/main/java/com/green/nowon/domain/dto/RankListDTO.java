package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.EmployeeEntity;
import com.green.nowon.domain.entity.RankEntity;
import com.green.nowon.domain.entity.RankImgEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RankListDTO {
	private long no;
	private String position;
	private long basePay;
	private long nightPay;
	private long overPay;

	private long annualPay;
	private int annualDay;

	private String defImg;

	public RankListDTO(RankEntity e) {
		this.no = e.getNo();
		this.position = e.getPosition();
		this.basePay = e.getBasePay();
		this.nightPay = e.getNightPay();
		this.overPay = e.getOverPay();
		this.annualPay = e.getAnnualPay();
		this.annualDay = e.getAnnualDay();
		this.defImg=e.defImg().getUrl()+e.defImg().getNewName();
	}



}
