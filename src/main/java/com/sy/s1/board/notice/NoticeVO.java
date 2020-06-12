package com.sy.s1.board.notice;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.sy.s1.board.BoardVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "notice")		// 테이블 명 notice
@Entity
@DynamicUpdate
public class NoticeVO extends BoardVO{
	
	// OneToOne : 자기 자신(One) To 상대방(One)
	// Notice 컬럼 하나에 여러 개의 파일 존재
	
	// fetch.EAGER	: 조회 시 noticeFileVO도 함께 가져옴
	// fetch.LAZY	: 조회 시 NoticeVO만 불러옴, 사용 시 noticeFileVO도 불러오는 것
	@OneToMany(mappedBy = "noticeVO", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<NoticeFileVO> boardFiles;
	
	
}
