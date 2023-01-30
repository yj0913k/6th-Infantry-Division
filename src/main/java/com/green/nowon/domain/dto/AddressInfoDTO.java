package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.AddressEntity;

import lombok.Setter;


@Setter
public class AddressInfoDTO {
	
	
	private String postcode;
	private String roadAddress;
	private String jibunAddress;
	private String detailAddress;
	
	public AddressEntity toEntity() { //Address엔티티로 고쳐야함 나중에
		return AddressEntity.builder()
				.postcode(postcode)
				.roadAddress(roadAddress)
				.jibunAddress(jibunAddress)
				.detailAddress(detailAddress)
				.build();
	}

}
