package com.sy.s1.board.qna;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sy.s1.util.Pager;

@Service
@Transactional(rollbackOn = Exception.class)
public class QnaService {
	
	@Autowired
	private QnaRepository qnaRepository;
	
	
	public QnaVO boardWrite (QnaVO qnaVO) throws Exception{
		// 원본글
		// ref = 자기 자신의 글번호
		// step, depth = 0
		
		qnaVO = qnaRepository.save(qnaVO);
		qnaVO.setRef(qnaVO.getNum());
		// 업데이트된 ref 다시 저장
		return qnaRepository.save(qnaVO);
	}
	
	
	public Page<QnaVO> boardList(Pager pager) throws Exception{
		pager.makeRow();
		Pageable pageable = PageRequest.of((int)pager.getStartRow(), pager.getSize(),Sort.by("ref").descending().and(Sort.by("step").ascending()));
		
		Page<QnaVO> page = null;
		if(pager.getKind().equals("title")) {
			page = qnaRepository.findByTitleContaining(pager.getSearch(), pageable);
		} else if(pager.getKind().equals("contents")) {
			page = qnaRepository.findByContentsContaining(pager.getSearch(), pageable);
		} else {
			page = qnaRepository.findByWriterContaining(pager.getSearch(), pageable);
		}
		
		// total 페이지 갯수 받아오는 쿼리문 작성하지 않아도 됨
		pager.makePage(page.getTotalPages());
		
		return page;
	}
	

}
