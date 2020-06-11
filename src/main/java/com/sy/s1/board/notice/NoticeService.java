package com.sy.s1.board.notice;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sy.s1.board.BoardVO;
import com.sy.s1.member.MemberFileVO;
import com.sy.s1.util.FileManager;
import com.sy.s1.util.FilePathGenerator;
import com.sy.s1.util.Pager;

@Service
public class NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;
	@Autowired
	private FileManager fileManager;
	@Autowired
	private FilePathGenerator filePathGenerator;
	
	// Pageable pageable,String search
	public List<NoticeVO> getSelectList(Pager pager) throws Exception {	
		pager.makeRow();
		// 검색 결과만 카운트 해오기
		long totalCount = noticeRepository.count();
		
		List<NoticeVO> ar = new ArrayList<NoticeVO>();
		Pageable pageable = PageRequest.of((int)pager.getStartRow(), 10, Sort.Direction.DESC, "num");
		
		if(pager.getKind().equals("writer")) {
			ar = noticeRepository.findByWriterContainingOrderByNumDesc(pageable,pager.getSearch());
			totalCount = noticeRepository.countByWriterContaining(pager.getSearch());
		} else if(pager.getKind().equals("contents")) {
			ar = noticeRepository.findByContentsContainingOrderByNumDesc(pageable,pager.getSearch());
			totalCount = noticeRepository.countByContentsContaining(pager.getSearch());
		} else if(pager.getKind().equals("title")) {
			ar = noticeRepository.findByTitleContainingOrderByNumDesc(pageable,pager.getSearch());
			totalCount = noticeRepository.countByTitleContaining(pager.getSearch());
		}		
		pager.makePage(totalCount);
		
		return ar;
	}
	
	
	public BoardVO getSelectOne(NoticeVO noticeVO) throws Exception {
//		this.hitUpdate(boardVO.getNum());
		return noticeRepository.findById(noticeVO.getNum()).get();
	}
	
	
	public NoticeVO setInsert(NoticeVO noticeVO,MultipartFile[] files) throws Exception {
		File file = filePathGenerator.getUseClassPathResource("/upload/notice");
		List<NoticeFileVO> noticeFileVOs = new ArrayList<NoticeFileVO>();
		for(MultipartFile multipartFile: files) {
			// 0KB 파일 저장되는 것 처리
			if(multipartFile.getSize()<=0) {
				continue;
			}
			String fileName = fileManager.saveFileCopy(multipartFile, file);
			
			NoticeFileVO noticeFileVO = new NoticeFileVO();
			noticeFileVO.setFileName(fileName);
			noticeFileVO.setOriName(multipartFile.getOriginalFilename());
			noticeFileVO.setNoticeVO(noticeVO);
			noticeFileVOs.add(noticeFileVO);
			System.out.println(fileName);
		}
		noticeVO.setNoticeFileVOs(noticeFileVOs);		
		return noticeRepository.save(noticeVO);
	}
	
}
