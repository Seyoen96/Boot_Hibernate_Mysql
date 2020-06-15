package com.sy.s1.board.qna;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface QnaRepository extends JpaRepository<QnaVO, Long> {
	
	// JPQL은 메서드명 마음대로 설정 가능
	// update

	//@Query(nativeQuery = true, value = "update qna set title=?1")
	//@Query(value = "update QnaVO q set q.title=?1, q.contents=?2 where q.num=?3")
	//						테이블명 대신 VO이름, 뒤에는 별칭 줘야함
	//									숫자로 가져올 때  -> 매개변수 순서 맞춰추기
	
	
	
	//Update
	@Transactional
	@Modifying
	@Query(value = "update QnaVO q set q.title=?1, q.contents=?2 where q.num=?3")
	void qnaUpdate(String title,String contents, Long num);
		
	//Update
	@Transactional(rollbackOn = Exception.class)
	@Modifying
	@Query(nativeQuery = true, value = "update qna set title=?1")
	void qnaUpdate2(String title,String contents, Long num);
	
	
	@Query(nativeQuery = true, value="select q QnaVO q where q.num=:num")
	QnaVO qnaSelect(Long num);
	/*
	@Query("select q.title, q.writer from QnaVO q where q.num=:num")
	Object[] qnaSelect2(Long num);
	*/
	
	// title 검색
	Page<QnaVO> findByTitleContaining(String search, Pageable pageable);
	// contents
	Page<QnaVO> findByContentsContaining(String search, Pageable pageable);
	// writer
	Page<QnaVO> findByWriterContaining(String search, Pageable pageable);

	
	// ref가  부모의 ref와 같고, step이 부모의 step보다 큰 것 찾기 
	List<QnaVO> findByRefAndStepGreaterThan(long ref, long step);
	
}