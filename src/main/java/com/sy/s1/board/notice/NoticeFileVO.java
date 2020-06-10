package com.sy.s1.board.notice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "noticeFile")
@Entity
public class NoticeFileVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long fileNum;
	@Column
	private String fileName;
	@Column
	private String oriName;
	
	
	// OneToOne 	: 자기 자신(One) To 상대방(One)
	// ManyToOne	: 여러 개의 데이터에 한 컬럼 연결
	// Notice 컬럼 하나에 여러 개의 파일 존재
	@ManyToOne
	@JoinColumn(name = "num")
	private NoticeVO noticeVO;
	
	
}
