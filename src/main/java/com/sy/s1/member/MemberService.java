package com.sy.s1.member;

import java.io.File;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.sy.s1.util.FileManager;
import com.sy.s1.util.FilePathGenerator;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private FilePathGenerator filePathGenerator;
	@Autowired
	private FileManager fileManager;
	
	
	// 로그인
	public MemberVO memberLogin(MemberVO memberVO) throws Exception{
		return memberRepository.findByIdAndPw(memberVO.getId(), memberVO.getPw());
	}
	
	// 회원 가입
	public MemberVO memberJoin(MemberVO memberVO, MultipartFile[] files) throws Exception{
		
		File file = filePathGenerator.getUseClassPathResource("/upload/member");
		
		for(MultipartFile multipartFile: files) {
			// 0KB 파일 저장되는 것 처리
			if(multipartFile.getSize()<=0) {
				continue;
			}

			String fileName = fileManager.saveFileCopy(multipartFile, file);

			MemberFileVO memberFileVO = new MemberFileVO();
			memberFileVO.setMemberVO(memberVO);
			memberFileVO.setFileName(fileName);
			memberFileVO.setOriName(multipartFile.getOriginalFilename());
			memberVO.setMemberFileVO(memberFileVO);
			memberFileVO.setMemberVO(memberVO);
			
			memberVO = memberRepository.save(memberVO);
			System.out.println(fileName);
		}
		
		
		return memberVO;
	}
	
	// 멤버 정보 select
	public MemberVO memberSelect(String id) throws Exception{
		return memberRepository.getOne(id);
	}
	
	// 멤버 수정
	public MemberVO memberUpdate(MemberVO memberVO) throws Exception{
		return memberRepository.save(memberVO);
		
	}
	
	public void memberDelete(String id) throws Exception{
		memberRepository.deleteById(id);
	}
	
	
	//검증 메서드 추가
	public boolean memberError(MemberVO memberVO, BindingResult bindingResult) throws Exception{
		boolean result = false;			// 에러 없음
		//1. 기본 annotation 제공 검증
		if(bindingResult.hasErrors()) {
			System.out.println("error 1");
			result = true;
		}			
		//2. pw 체크 일치 검증
		if(!memberVO.getPw().equals(memberVO.getPwCheck())) {
			System.out.println("error 2");
			bindingResult.rejectValue("pwCheck", "memberVO.password.notEqual");
			result= true;
		} 			
		//3. ID 중복 검증
			//DB에서 ID 조회
		if(memberRepository.existsById(memberVO.getId())) {
			System.out.println("error 3");
			bindingResult.rejectValue("id","memberVO.id.equal");
			result = true;
		}
		return result;
	}	
	

	
}
