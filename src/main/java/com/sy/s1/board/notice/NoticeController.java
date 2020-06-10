package com.sy.s1.board.notice;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sy.s1.board.BoardVO;
import com.sy.s1.util.Pager;

@Controller
@RequestMapping("/notice/**/")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "notice";
	}
	
	
	@GetMapping("noticeList")
	//@PageableDefault(page = 0,size = 10,sort = {"num"}, direction = Direction.DESC) Pageable pageable, @RequestParam(defaultValue = "") String search
	public ModelAndView getSelectList(Pager pager) throws Exception{
		ModelAndView mv = new ModelAndView();
		//								  (page,size,Sort,column)
//		PageRequest.of(page, size);
//		pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "num");
		List<NoticeVO> ar = noticeService.getSelectList(pager);
		mv.addObject("list", ar);
		mv.addObject("path", "List");
//		mv.addObject("pager", pager);
		mv.setViewName("board/boardList");
		return mv;
	}
	
	@GetMapping("noticeSelect")
	public ModelAndView getSelectOne(NoticeVO noticeVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		noticeVO = (NoticeVO)noticeService.getSelectOne(noticeVO);
		mv.addObject("vo",noticeVO);
		mv.addObject("noticeFileVOs",noticeVO.getNoticeFileVOs());
		mv.setViewName("board/boardSelect");
		return mv;
	}
	
	@GetMapping("noticeWrite")
	public ModelAndView setInsert() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/boardWrite");
		mv.addObject("path", "Write");
		mv.addObject("boardVO",new BoardVO());
		return mv;
	}
	
	@PostMapping("noticeWrite")
	public ModelAndView setInsert(@Valid NoticeVO noticeVO,BindingResult bindingResult,MultipartFile[] files,RedirectAttributes rd) throws Exception{
		// insert 정보 전에 잘 받아오는지 검증
		ModelAndView mv = new ModelAndView();
		if(bindingResult.hasErrors()) {
			// 에러 발생 시 입력 페이지로 이동
			mv.setViewName("board/boardWrite");
			mv.addObject("path", "Write");
		} else {
			noticeVO = noticeService.setInsert(noticeVO,files);
			int res = 1;
			if(noticeVO==null) {
				// 등록 실패
				res=0;
			}
			rd.addFlashAttribute("result", res);
			mv.setViewName("redirect:./noticeList");
		}
		return mv; 
	}
	
	
	
}
