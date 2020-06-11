package com.sy.s1.board.qna;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sy.s1.board.BoardVO;
import com.sy.s1.board.notice.NoticeVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name="qna")
@Entity
public class QnaVO extends BoardVO{
	
	@Column
	private Long ref;
	@Column
	private Long step;
	@Column
	private Long depth;
	
	// mappedBy에는 변수명을 넣어줌
	@OneToMany(mappedBy = "qnaVO", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<QnaFileVO> qnaFileVOs;
	
}
