package com.green.nowon.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class AddressEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;


	//private String AddressName; // 주소지 이름
	//나중에 잘 안되면, myShop에 daum.js 내용 확인해볼것
	//Delivery 엔티티에서 컬럼을 어느정도 수정을 했기때문

	private String postcode; //우편번호
	private String roadAddress; // 길
	private String jibunAddress;// 지번
	private String detailAddress;// 상세주소
	private String extraAddress; // 추가 주소

}