package com.sy.s1.member;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "member")		//create table member
public class MemberVO {
	
	@Id			// PK
//	@NotEmpty
	private String id;
	
	@Column		// 일반 컬럼명
//	@NotEmpty
	private String pw;
	
	@Transient	//테이블에서 제외
//	@NotEmpty
	private String pwCheck;
	
	@Column
//	@NotEmpty
	private String name;
	
	@Column
//	@NotEmpty
	private String email;
	
	@Column
//	@NotEmpty
	private String phone;
	
	// member와 memberFile이 일대일 관계
	// mappedBy = " Join하는 Entity에 선언된 자기 자신의 Entity 변수명 "
	@OneToOne(mappedBy = "memberVO", cascade = CascadeType.ALL)
	private MemberFileVO memberFileVO;
	
	
}
