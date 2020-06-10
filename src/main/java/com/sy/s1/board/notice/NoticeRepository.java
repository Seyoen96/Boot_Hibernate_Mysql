package com.sy.s1.board.notice;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeVO, Long>{
	
		
	// select * from notice where num>0 order by num desc
	public List<NoticeVO> findByNumGreaterThanOrderByNumDesc(long num);
	
	// select * from notice where num between 6 and 10
	public List<NoticeVO> findByNumBetweenOrderByNumDesc(long start, long end);
	
	// 검색어로 찾아오기 + pageable
	// select * from notice where title like?? order by num desc
	public List<NoticeVO> findByTitleContainingOrderByNumDesc(Pageable pageable,String search);
	// select * from notice where contents like ?? order by num desc
	public List<NoticeVO> findByContentsContainingOrderByNumDesc(Pageable pageable,String search);
	// writer
	public List<NoticeVO> findByWriterContainingOrderByNumDesc(Pageable pageable,String search);
	
	// totalCount by title
	public int countByTitleContaining(String search);
	public int countByContentsContaining(String search);
	public int countByWriterContaining(String search);
	
}
