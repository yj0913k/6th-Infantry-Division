package com.green.nowon.security;

import com.green.nowon.repository.EmployeeEntityRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;


@Log4j2
public class MyUserDetailsService implements UserDetailsService {
	 
	
	@Autowired
	private EmployeeEntityRepository employeeEntityRepository;
	
	//DB테이블에서 인증처리하기위한 메서드
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return new MyUserDetails(employeeEntityRepository.findByEmailAndDeleted(username,false).orElseThrow(
				()->new UsernameNotFoundException("존재하지않는 이메일입니다.")));//noSuchElementException
				
	
	}

	
	
	
	
	
	
	
	
	
	
	
}
