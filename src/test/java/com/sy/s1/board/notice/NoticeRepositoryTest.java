package com.sy.s1.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoticeRepositoryTest {
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	private NoticeVO noticeVO;
	private NoticeFileVO noticeFileVO;
	
//	@BeforeEach
	public void beforEach() {			
		noticeVO = new NoticeVO();
		noticeVO.setTitle("title");
		noticeVO.setWriter("writer");
		noticeVO.setContents("contents");
		
		List<NoticeFileVO> ar = new ArrayList<NoticeFileVO>();
		NoticeFileVO noticeFileVO = new NoticeFileVO();
		noticeFileVO.setFileName("fileName");
		noticeFileVO.setOriName("oriName");
		noticeFileVO.setNoticeVO(noticeVO);
		ar.add(noticeFileVO);
		
		noticeFileVO = new NoticeFileVO();
		noticeFileVO.setFileName("fileName2");
		noticeFileVO.setOriName("oriName2");
		noticeFileVO.setNoticeVO(noticeVO);
		ar.add(noticeFileVO);
		
		noticeVO.setNoticeFileVOs(ar);
		
		
	}
	
//	@Test
	public void insertTest() throws Exception{
		noticeVO = noticeRepository.save(noticeVO);
		assertNotNull(noticeVO);
	}
	
//	@Test
	public void insertTest2() throws Exception{
		for(int i =1; i<20;i++) {
			NoticeVO noticeVO = new NoticeVO();
			noticeVO.setContents("contents"+i);
//			noticeVO.setHit(0);
			noticeVO.setTitle("title"+i);
			noticeVO.setWriter("writer"+i);
			noticeVO = noticeRepository.save(noticeVO);
			
		}
	}
	
	
//	@Test
	public void updateTest() throws Exception{
		noticeVO.setNum(3);
		noticeVO.setTitle("update title");
		noticeVO = noticeRepository.save(noticeVO);
		assertNotNull(noticeVO);
	}
	
//	@Test
	public void deleteTest() throws Exception{
		noticeRepository.deleteById(2L);
		// delete 되었는지 확인 (해당 데이터가 있는지 체크)
		boolean check = noticeRepository.existsById(2L);
		assertEquals(false, check);
	}
	
	// selectList
//	@Test
	public void selectListTest() throws Exception{
		List<NoticeVO> ar = noticeRepository.findAll();
		for(NoticeVO noticeVO:ar) {
			System.out.println(noticeVO.getTitle());
		}
		assertNotEquals(0, ar.size());
	}
	
	//selectOne
	@Test
	public void selectOneTest() throws Exception{
		Optional<NoticeVO> opt = noticeRepository.findById(25L);
		// 불러왔는지 확인
//		if(opt.isPresent()) {
		noticeVO = opt.get();
		if(noticeVO!=null) {
			System.out.println("FileName: "+noticeVO.getNoticeFileVOs().get(0).getFileName());
		}
		assertNotNull(noticeVO);
	}
	
	/*
	@Test
	public void customTest() throws Exception{
//		List<NoticeVO> ar = noticeRepository.findByNumGreaterThanOrderByNumDesc(0L);
//		List<NoticeVO> ar = noticeRepository.findByNumBetweenOrderByNumDesc(6, 10);
		List<NoticeVO> ar = noticeRepository.findByTitleContainingOrderByNumDesc("title");
		for(NoticeVO noticeVO:ar) {
			System.out.println("Title: "+noticeVO.getTitle());
		}
		assertNotEquals(0, ar.size());
	}
	
	*/
	
}
