package com.sy.s1.board;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor		// default 생성자 생성
@AllArgsConstructor		// 매개변수 갖는 생성자 생성
@MappedSuperclass		// 테이블을 만들지 않고, 테이블 만드는 클래스의 부모역할
public class BoardVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long num;
	@Column
	private String title;
	@Column
	private String writer;
	@Column
	private String contents;
	@Column
	@CreationTimestamp		// insert 시 시간
//	@UpdateTimestamp		// update 시 시간
	private Date regDate;
	@Column
	private long hit;
	

}
