package com.green.nowon.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.dto.AddressInfoDTO;
import com.green.nowon.repository.DeliveryEntityRepository;
import com.green.nowon.repository.DepartmentEntityRepository;
import com.green.nowon.service.AddressService;

@Service
public class AddressServiceProcess implements AddressService {

//	@Autowired
//	private ItemEntityRepository itemRepo;
	
	@Autowired
	private DeliveryEntityRepository deliveryRepo;
	
	@Autowired
	private DepartmentEntityRepository departmentRepo;
	

	@Override
	public void addressInfoSave(AddressInfoDTO dto) {
		deliveryRepo.save(dto.toEntity());
	}

	

}
