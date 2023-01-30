package com.green.nowon.security;

import com.green.nowon.domain.entity.DepartmentEntity;
import com.green.nowon.domain.entity.EmployeeEntity;
import com.green.nowon.domain.entity.EmployeeImgEntity;
import com.green.nowon.domain.entity.RankEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MyUserDetails extends User {

	private String rankImg;
	private long mno;
	private String email;
	private String name;
	private String nickName;
	private DepartmentEntity department;
	private String rank;
//	private List<EmployeeImgEntity> imgs=new ArrayList<>();
	private String imgs;

//	private String rank;
	//principal 객체에 들어가게된다 .
	public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		
	}

	//멤버에대한 인증된 정보들을 가지고오는거에요
	public MyUserDetails(EmployeeEntity entity) {
		this(entity.getEmail(),entity.getPass(),entity.getRoles() //Set<MyRole> —> Set<GrantedAuthority>
				.stream() 
				.map(myRole->new SimpleGrantedAuthority(myRole.getRole()) )// Set<GrantedAuthority>
				.collect(Collectors.toSet()));
		//this는 무조건 생성자에 첫번째 위치 줄에 있어야한다. !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

//		String img = entity.getImgs().get(0).getUrl();
//		String img = entity.defImg().getUrl()+entity.defImg().getNewName();
//
		this.email=entity.getEmail();
		this.name=entity.getName();
		//this.nickName=entity.getNickName();//null이 허용되어있기때문에
		this.mno=entity.getNo();//mno
		/////////////////영준 수정////////////////
		this.department=entity.getDepartment();
		this.rank=entity.getRank().getPosition();
		this.imgs= entity.defImg().getUrl()+entity.defImg().getNewName();

		this.rankImg=entity.getRank().defImg().getUrl()+entity.getRank().defImg().getNewName();


	}


}
