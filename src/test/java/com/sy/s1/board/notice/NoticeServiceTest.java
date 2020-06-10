package com.sy.s1.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
class NoticeServiceTest {

	@Autowired
	private NoticeService noticeService;
	
	@Test
	void boardListTest() throws Exception{
		Pageable pageable = PageRequest.of(1, 10, Sort.Direction.DESC, "num");
		String search ="";
		/*
		List<NoticeVO> ar = noticeService.getSelectList(pageable,search);
		for(NoticeVO noticeVO:ar) {
			System.out.println("num: "+noticeVO.getNum());
			System.out.println("title: "+noticeVO.getTitle());
		}
		*/
	}

}
