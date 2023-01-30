package com.green.nowon.domain.entity;

import com.green.nowon.security.MyRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class EmployeeEntity {// 유저엔티티

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     long no;
     @Column(nullable = false)
     private String email; //이메일
     @Column(nullable = false)
     private String name; //이름
     @Column(nullable = false)
     private String pass; //비밀번호
     @Column(nullable = false)
     private String joinDate; //입대일


     @Column(nullable = false)
     private String birth;

     private boolean deleted; //삭제여부 1:가입중 0: 탈퇴
     @Column(nullable = false) //연락처
     private String phone;


     //군번

     @Column(nullable = false, unique = true)
     private String number; //군번
     //주소

     //계급번호
     @JoinColumn
     @ManyToOne
     private RankEntity rank; //rank_no

     //소속번호
     @JoinColumn
     @ManyToOne
     private DepartmentEntity department; //department id 소속엔티티


     @Builder.Default
     @OneToMany(mappedBy = "employee")
     private List<EmployeeImgEntity> imgs=new ArrayList<>();

     public EmployeeImgEntity defImg() {
          for(EmployeeImgEntity img:imgs) {
               if(img.isDefImg()) return img;

          }
          return imgs.get(0);//만약에 대표이지미지 없으면 첫번째이미지로
     }

     @CollectionTable(name = "my_role")
     @Builder.Default
     @Enumerated(EnumType.STRING) //설정하지 않으면 숫자(ORDINAL)
     @ElementCollection(fetch = FetchType.EAGER)   //1:N 관계
     Set<MyRole> roles=new HashSet<>();

     public EmployeeEntity addRole(MyRole role ) {
          roles.add(role);
          return this;
     }


     public EmployeeEntity setRank(RankEntity rank) {
          this.rank=rank;
          return this;
     }

}