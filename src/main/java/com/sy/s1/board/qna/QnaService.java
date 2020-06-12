package com.sy.s1.board.qna;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sy.s1.util.FileManager;
import com.sy.s1.util.FilePathGenerator;
import com.sy.s1.util.Pager;

@Service
@Transactional(rollbackOn = Exception.class)
public class QnaService {
	
	@Autowired
	private QnaRepository qnaRepository;
	@Autowired
	private FilePathGenerator filePathGenerator;
	@Autowired
	private FileManager fileManager;
	
	@Value("${board.qna}")
	private String filePath;
	
	// reply
	public QnaVO boardReply (QnaVO qnaVO,MultipartFile[] files) throws Exception{
		// 입력한 정보 저장을 위한 VO 생성
		QnaVO childVO = new QnaVO(); 
		childVO.setTitle(qnaVO.getTitle());
		childVO.setWriter(qnaVO.getWriter());
		childVO.setContents(qnaVO.getContents());
		
		// update
		// 부모의 ref와 같고, step이 부모의 step보다 큰 것들은 step 1씩 증가
		
		//1. 부모의 정보 조회
		qnaVO = qnaRepository.findById(qnaVO.getNum()).get();
		List<QnaVO> ar = qnaRepository.findByRefAndStepGreaterThan(qnaVO.getRef(), qnaVO.getStep());
		
		List<QnaFileVO> qnaFileVOs = new ArrayList<QnaFileVO>();
		File file = filePathGenerator.getUseClassPathResource(filePath);
		for(MultipartFile multipartFile: files) {
			QnaFileVO qnaFileVO = new QnaFileVO();
			String fileName = fileManager.saveFileCopy(multipartFile, file);
			qnaFileVO.setFileName(fileName);
			qnaFileVO.setOriName(multipartFile.getOriginalFilename());
			qnaFileVO.setQnaVO(qnaVO);
			qnaFileVOs.add(qnaFileVO);
			
		}
		qnaVO.setBoardFiles(qnaFileVOs);

		for(QnaVO q : ar) {
			q.setStep(q.getStep()+1);
		}
		qnaRepository.saveAll(ar);	
		
		// 자기 자신의 ref는 		부모의 ref
		// 자기 자신의 step은		부모의 step+1
		// 자기 자신의 depth는 	부모의 depth+1
		childVO.setRef(qnaVO.getRef());
		childVO.setStep(qnaVO.getStep()+1);
		childVO.setDepth(qnaVO.getDepth()+1);
		qnaRepository.save(childVO);
		
		return childVO;
	}
	
	// Write
	public QnaVO boardWrite (QnaVO qnaVO,MultipartFile[] files) throws Exception{
		// 원본글
		// ref = 자기 자신의 글번호
		// step, depth = 0
		qnaVO = qnaRepository.save(qnaVO);
		qnaVO.setRef(qnaVO.getNum());
		// 업데이트된 ref 다시 저장
		
		List<QnaFileVO> qnaFileVOs = new ArrayList<QnaFileVO>();
		File file = filePathGenerator.getUseClassPathResource(filePath);
		for(MultipartFile multipartFile: files) {
			if(multipartFile.getSize()<=0) {
				continue;
			}
			QnaFileVO qnaFileVO = new QnaFileVO();
			String fileName = fileManager.saveFileCopy(multipartFile, file);
			qnaFileVO.setFileName(fileName);
			qnaFileVO.setOriName(multipartFile.getOriginalFilename());
			qnaFileVO.setQnaVO(qnaVO);
			qnaFileVOs.add(qnaFileVO);
			
		}
		qnaVO.setBoardFiles(qnaFileVOs);
		
		return qnaRepository.save(qnaVO);
	}
	
	// List
	public Page<QnaVO> boardList(Pager pager) throws Exception{
		pager.makeRow();
		Pageable pageable = PageRequest.of((int)pager.getStartRow(), pager.getSize(),Sort.by("ref").descending().and(Sort.by("step").ascending()));
		
		Page<QnaVO> page = null;
		if(pager.getKind().equals("title")) {
			page = qnaRepository.findByTitleContaining(pager.getSearch(), pageable);
		} else if(pager.getKind().equals("contents")) {
			page = qnaRepository.findByContentsContaining(pager.getSearch(), pageable);
		} else {
			page = qnaRepository.findByWriterContaining(pager.getSearch(), pageable);
		}	
		// total 페이지 갯수 받아오는 쿼리문 작성하지 않아도 됨
		pager.makePage(page.getTotalPages());
		
		return page;
	}
	
	// select
	public QnaVO boardSelect (QnaVO qnaVO) throws Exception{

		// hit + 1  update
		qnaVO = qnaRepository.findById(qnaVO.getNum()).get();
		qnaVO.setHit(qnaVO.getHit()+1);
		
		return qnaRepository.save(qnaVO);
	}
	
	// delete
	public boolean boardDelete(QnaVO qnaVO) throws Exception{
		qnaRepository.deleteById(qnaVO.getNum());
		return qnaRepository.existsById(qnaVO.getNum());
	}
	
	public void boardUpdate(QnaVO qnaVO,MultipartFile[] files) throws Exception{
		qnaRepository.qnaUpdate(qnaVO.getTitle(), qnaVO.getContents(), qnaVO.getNum());
	}
	
	

}
