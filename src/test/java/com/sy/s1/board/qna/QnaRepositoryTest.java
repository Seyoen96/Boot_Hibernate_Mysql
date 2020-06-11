package com.sy.s1.board.qna;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QnaRepositoryTest {
	
	@Autowired
	private QnaRepository qnaRepository;
	
	@Test
	public void insertTest() {
		for(int i=1; i<150; i++) {
			QnaVO qnaVO = new QnaVO();
			qnaVO.setTitle("question~~"+i);
			qnaVO.setContents("review"+i);
			qnaVO.setWriter("writer"+i);
			
			qnaVO = qnaRepository.save(qnaVO);
			
		}
		
		
//		assertNotNull(qnaVO);
	}

}
