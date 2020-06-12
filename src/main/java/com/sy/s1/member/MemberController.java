package com.sy.s1.member;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sy.s1.util.FilePathGenerator;

@Controller
@RequestMapping("/member/**/")
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	
	@GetMapping("memberUpdate")
	public ModelAndView memberUpdate(String id, ModelAndView mv) throws Exception{
		MemberVO memberVO = memberService.memberSelect(id);
		mv.addObject("vo", memberVO);
		mv.setViewName("member/memberUpdate");
		return mv;
	}
	
	@PostMapping("memberUpdate")
	public ModelAndView memberUpdate(MemberVO memberVO,ModelAndView mv) throws Exception{
		memberVO = memberService.memberUpdate(memberVO);
		if(memberVO!=null) {
			mv.setViewName("redirect:../");
		} else {
			mv.setViewName("member/memberUpdate");
		}
		return mv;
	}
	
	@GetMapping("memberDelete")
	public String memberDelete (String id,HttpSession session) throws Exception{
		
		memberService.memberDelete(id);
		session.invalidate();
	
		return "redirect:/";
	}
	
	@GetMapping("memberPage")
	public ModelAndView memberPage(ModelAndView mv,HttpSession session) throws Exception{
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		mv.addObject("vo", memberVO);
		mv.setViewName("member/memberPage");
		return mv;
	}
	
	@GetMapping("memberJoin")
	public ModelAndView memberJoin() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.addObject("memberVO", new MemberVO());
		mv.setViewName("member/memberJoin");
		return mv;
	}
	
	@PostMapping("memberJoin")
	public ModelAndView memberJoin(@Valid MemberVO memberVO,BindingResult bindingResult, MultipartFile[] files,ModelAndView mv) throws Exception{		
		if(memberService.memberError(memberVO, bindingResult)) {
			// 에러 발생 시
			mv.setViewName("member/memberJoin");
		} else {
			memberVO = memberService.memberJoin(memberVO,files);
			mv.setViewName("redirect:../");
		}		
		return mv;
	}
	
	
	@GetMapping("memberLogin")
	public void memberLogin() throws Exception{
	}
	
	@PostMapping("memberLogin")
	public ModelAndView memberLogin(MemberVO memberVO, ModelAndView mv,HttpSession session) throws Exception{
		memberVO = memberService.memberLogin(memberVO);
		if(memberVO!=null) {
			// login 성공
			session.setAttribute("member", memberVO);
			mv.setViewName("redirect:../");
		} else {
			mv.addObject("result", "login fail..");
			mv.addObject("path", "member/memberLogin");
			mv.setViewName("template/result");
		}
		return mv;
	}
	
	@GetMapping("memberLogout")
	public String memberLogout(HttpSession session) throws Exception{
		session.invalidate();
		return "redirect:../";
	}
	
	
}
