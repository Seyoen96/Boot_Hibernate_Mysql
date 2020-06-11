package com.sy.s1.board.qna;

import java.util.List;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public ModelAndView boardList(@PageableDefault(size = 10, page = 0, direction = Direction.DESC) Pageable pageable,String kind, String search) throws Exception {
//															  page = 0 이 첫번째 페이지
		ModelAndView mv = new ModelAndView();
		System.out.println("kind: "+kind);
		System.out.println("search: "+search);
		if(search==null) {
			search = "";
		}
		Page<QnaVO> page = qnaService.boardList(pageable,kind,search);
		
//		System.out.println(page.getContent().size());
//		System.out.println(page.getSize());
//		System.out.println("Elements: "+page.getTotalElements());
//		System.out.println("TotalPage: "+page.getTotalPages());
//		// 다음 페이지가 존재하는지 여부   hasNext
//		System.out.println("hasNext: "+page.hasNext());
//		System.out.println("hasPrevious: "+page.hasPrevious());
//		System.out.println("hasContent: "+page.hasContent());
//		System.out.println("First: "+page.isFirst());
//		System.out.println("Last: "+page.isLast());
			
		
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
