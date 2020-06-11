package com.sy.s1.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
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
	
	public Page<QnaVO> boardList(Pageable pageable) throws Exception{
		return qnaRepository.findAll(pageable);
	}
	

}
