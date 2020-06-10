package com.sy.s1.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="memberFile")
@Entity
public class MemberFileVO {
	  
	@Id
	// 자동 증가하는 옵션, 시퀀스 사용시 X
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private long fileNum;
	@Column
	private String fileName;
	@Column
	private String oriName;
	
	@OneToOne
	@JoinColumn(name = "id")	//member테이블의 join할 컬럼명
	// mappedBy에 선언된 이름과 매핑
	private MemberVO memberVO;
	
}
