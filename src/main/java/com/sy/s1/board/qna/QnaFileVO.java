package com.sy.s1.board.qna;

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
@Entity
@Table(name = "qnaFile")
public class QnaFileVO {
	
	@Id
	// workbench에서 테이블 생성 시 AI를 주었기 때문에 생략 가능
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long fileNum;
	private String fileName;
	private String oriName;
	
	@ManyToOne
	@JoinColumn(name = "num")
	private QnaVO qnaVO;
	
	
}
