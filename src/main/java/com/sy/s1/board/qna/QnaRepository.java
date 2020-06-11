package com.sy.s1.board.qna;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<QnaVO, Long> {
	
	public Page<QnaVO> findAllOrderByNumDesc(Pageable pageable);
	
	public Page<QnaVO> findByTitleContainingOrderByNumDesc(Pageable pageable, String search);
	public Page<QnaVO> findByWriterContainingOrderByNumDesc(Pageable pageable, String search);
	public Page<QnaVO> findByContentsContainingOrderByNumDesc(Pageable pageable, String search);

}