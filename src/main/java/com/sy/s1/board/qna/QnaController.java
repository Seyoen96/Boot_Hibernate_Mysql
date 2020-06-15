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
import org.springframework.web.multipart.MultipartFile;
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
		
		// 전체 페이지의 갯수 가져오기
		page.getTotalPages();
		
		int lastPage = page.getTotalPages()-1;
		mv.addObject("page", page);
		mv.addObject("pager", pager);
		mv.setViewName("board/boardList");
		
//		if(page.getNumber() > lastPage) {
//			// 마지막 페이지보다 큰 페이지 수를 입력한 경우 처리
//			mv.setViewName("redirect:./qnaList?page=0");	
//		}
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
	public ModelAndView boardWrite(QnaVO qnaVO,MultipartFile[] files) throws Exception{
		ModelAndView mv = new ModelAndView();
		qnaVO = qnaService.boardWrite(qnaVO,files);
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
	
	@GetMapping("qnaSelect")
	public ModelAndView boardSelect(QnaVO qnaVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		qnaVO = qnaService.boardSelect(qnaVO);

		mv.addObject("vo", qnaVO);
		mv.setViewName("board/boardSelect");
		return mv;
	}
	
	@GetMapping("qnaReply")
	public ModelAndView boardReply(QnaVO qnaVO,ModelAndView mv) throws Exception{
		mv.addObject("boardVO",qnaVO);
		mv.addObject("path", "Reply");
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@PostMapping("qnaReply")
	public ModelAndView boardReply(QnaVO qnaVO,MultipartFile[] files) throws Exception{
		ModelAndView mv = new ModelAndView();
		qnaVO = qnaService.boardReply(qnaVO,files);
		if(qnaVO==null) {
			mv.addObject("result", "답글 작성 실패");
			mv.addObject("path", "qnaSelect");
		} else {
			mv.addObject("result", "답글 작성 완료");
			mv.addObject("path", "qnaList");
		}
		mv.setViewName("template/result");
		return mv;
	}
	
	@GetMapping("qnaDelete")
	public String boardDelete(QnaVO qnaVO) throws Exception{
		boolean result = qnaService.boardDelete(qnaVO);
		return "qnaList";
	}
		
	@GetMapping("qnaUpdate")
	public ModelAndView boardUpdate(QnaVO qnaVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		qnaVO = qnaService.boardSelect(qnaVO);
		mv.addObject("boardVO", qnaVO);
		mv.addObject("path", "Update");
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@PostMapping("qnaUpdate")
	public String boardUpdate(QnaVO qnaVO, ModelAndView mv, MultipartFile[] files) throws Exception{
		qnaService.boardUpdate(qnaVO, files);
		return "redirect:./qnaList";
	}
	
	@GetMapping("qnaRedirect")
	public ModelAndView qnaRedirect(ModelAndView mv) throws Exception{
		mv.addObject("result", "권한이 없습니다");
		mv.addObject("path", "qna/qnaList");
		mv.setViewName("template/result");
		return mv;
	}
	
	
}
