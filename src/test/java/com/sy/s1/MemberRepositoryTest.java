package com.sy.s1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Example;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sy.s1.member.MemberFileVO;
import com.sy.s1.member.MemberRepository;
import com.sy.s1.member.MemberVO;



@SpringBootTest
class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;
	
//	@Test
	void idCheck() {
		boolean check = memberRepository.existsById("sy");
		MemberVO memberVO = new MemberVO();
//		check = memberRepository.exists(example);
		
		System.out.println(check);
	}
	
//	@Test
	void insertTest() {
		MemberVO memberVO = new MemberVO();
	
		memberVO.setId("aa");
		memberVO.setPw("ay");
		memberVO.setName("saon");
		memberVO.setEmail("aa@gmail.com");
		memberVO.setPhone("010-2333-2222");

		
//		MemberVO memberVO2 = new MemberVO();
//		memberVO2.setId("jb");
//		memberVO2.setPw("jb");
//		memberVO2.setName("jb");
//		memberVO2.setEmail("jb@gmail.com");
//		memberVO2.setPhone("010-5555-2222");
//		
//		List<MemberVO> ar = new ArrayList<MemberVO>();
//		ar.add(memberVO);
//		ar.add(memberVO2);
//		
//		
//		ar = memberRepository.saveAll(ar);
		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setFileName("fileName");
		memberFileVO.setOriName("oriName");	
		memberVO.setMemberFileVO(memberFileVO);
		
		// member 테이블에는 Insert 성공
		// memberFile 테이블의 id에는 NULL이 삽입됨
		memberRepository.save(memberVO);
		assertNotNull(memberVO);
	}
	
	@Test
	void insert2() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("sisi");
		memberVO.setPw("sisi");
		memberVO.setName("ssisi");
		memberVO.setEmail("siss@gmail.com");
		memberVO.setPhone("010-2222-2222");

		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setFileName("fileName");
		memberFileVO.setOriName("oriName");	
		memberVO.setMemberFileVO(memberFileVO);
		
		memberVO.setMemberFileVO(memberFileVO);
		memberFileVO.setMemberVO(memberVO);
		
		memberRepository.save(memberVO);
				
	}
	
	
	// id로 file도 함께 가져오기 추가
//	@Test
	void loginTest() {
		MemberVO memberVO = new MemberVO(); 
		memberVO = memberRepository.findByIdAndPw("sisi", "sisi");
		System.out.println(memberVO.getMemberFileVO().getFileName());
		System.out.println(memberVO.getMemberFileVO().getOriName());
		assertNotNull(memberVO);
	}
	
	
//	@Test
	void updateTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("sisi");
		memberVO.setEmail("0221si@gmail.com");
		
		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setFileNum(4);
		memberFileVO.setFileName("change File2 name");
		memberFileVO.setOriName("change Original2 Name");
		
		
		memberVO.setMemberFileVO(memberFileVO);
		memberFileVO.setMemberVO(memberVO);
		
		memberVO = memberRepository.save(memberVO);
		assertNotNull(memberVO);
	}
	
//	@Test
	void deleteTest() {
		memberRepository.deleteById("sisi");
	}
	
	

}
