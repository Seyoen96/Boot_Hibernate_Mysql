package com.sy.s1.board.qna;

import java.util.List;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sy.s1.board.notice.NoticeVO;
import com.sy.s1.util.Pager;

@Controller
@RequestMapping("/qna/**/")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "qna";
	}
	
	
	@GetMapping("qnaList")
	public ModelAndView boardList(Pager pager) throws Exception {
//															  page = 0 이 첫번째 페이지
		ModelAndView mv = new ModelAndView();
		
		Page<QnaVO> page = qnaService.boardList(pager);	
		
		int lastPage = page.getTotalPages()-1;
		mv.addObject("page", page);
		mv.setViewName("board/boardList");
		
		if(page.getNumber() > lastPage) {
			// 마지막 페이지보다 큰 페이지 수를 입력한 경우 처리
			mv.setViewName("redirect:./qnaList?page=0");	
		}
		return mv;
	}
	
	
	@GetMapping("qnaWrite")
	public ModelAndView boardWrite(ModelAndView mv) throws Exception{
		mv.addObject("path", "Write");
		mv.addObject("boardVO", new QnaVO());
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@PostMapping("qnaWrite")
	public ModelAndView boardWrite(QnaVO qnaVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		qnaVO = qnaService.boardWrite(qnaVO);
		if(qnaVO!=null) {
			mv.addObject("result", "write success!");
			mv.addObject("path", "qnaList");
		} else {
			mv.addObject("result", "write fail!");
			mv.addObject("path", "qnaWrite");
		}
		mv.setViewName("template/result");
		return mv;
	}
	
	
	
	
}
