package com.sy.s1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sy.s1.board.qna.QnaRepository;
import com.sy.s1.board.qna.QnaVO;
import com.sy.s1.member.MemberVO;

@Component
public class QnaInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	QnaRepository qnaRepository;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("interceptor in..");
		
		String method = request.getMethod();		
		if(method.equals("get")) {
			long num = Long.parseLong(request.getParameter("num"));
			QnaVO qnaVO = qnaRepository.findById(num).get();
			String writerID = qnaVO.getWriter();
			MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
			
			if(memberVO!=null) {
				System.out.println("get1: "+memberVO.getId());
				System.out.println("get2: "+writerID);
				
				if(!memberVO.getId().equals(writerID)) {
					response.sendRedirect("/qna/qnaRedirect");
					return false;
				} 
			} 
		}else {
			return true;
		}
		
		return true;
	}
	
	

}
