package com.sy.s1.board.qna;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
	
	public Page<QnaVO> boardList(Pageable pageable,String kind,String search) throws Exception{
		Page<QnaVO> page = qnaRepository.findAll(pageable);
		
		if(kind.equals("title")) {
			page = qnaRepository.findByTitleContainingOrderByNumDesc(pageable, search);
		} else if(kind.equals("writer")) {
			page = qnaRepository.findByWriterContainingOrderByNumDesc(pageable, search);
		}
		
		return page;
	}
	

}
